#Josh Wilcock

import pygame
import random
import math
import ai

pygame.init()
screen_height = 800
screen_width = 600
screen = pygame.display.set_mode([screen_width, screen_height])
text = pygame.font.Font("SourceCodePro-Regular.otf", 26)
backgroundimg = pygame.image.load("redbricks.jpg")
AI = ai.ai_model()

class Player(pygame.sprite.Sprite):
    
    def __init__(self, x, y):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("stickman.png")
        self.image = pygame.transform.scale(self.image, (30,50))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)
        self.health = 3

    def move(self, xmove, ymove):
        self.rect.x = self.rect.x + xmove #can be positive or negative depending on the direction the player is pressing
        if self.rect.y <= 700:
            self.rect.y = self.rect.y - ymove #negative because positive y moves up the screen

class Projectile(pygame.sprite.Sprite):
    
    def __init__(self, x, y):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("stickman.png")
        self.image = pygame.transform.scale(self.image, (5,10))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)

    def move(self):
        self.rect.y = self.rect.y + 10 #moves down the screen

class enemy(pygame.sprite.Sprite):
    def __init__(self, img, x, y):
        pass

    def move(self, xmove, ymove):
        self.rect.x = self.rect.x + xmove
        self.rect.y = self.rect.y - ymove #negative because positive y moves up the screen


class Ghost(enemy, pygame.sprite.Sprite):
    
    def __init__(self, x, y):
        img = "ghost.png"
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(img)
        self.image = pygame.transform.scale(self.image, (20, 20))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)
        self.move_count = 0
        self.score = 50

    def move_set(self, player):
        if self.move_count % 200 < 100: #moves 100 each way
            super().move(1, 1)
        else:
            super().move(-1, 1)
        self.move_count += 1

class Phantom(enemy, pygame.sprite.Sprite):
    
    def __init__(self, x, y):
        img = "phantom.png"
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(img)
        self.image = pygame.transform.scale(self.image, (20, 20))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)
        self.move_count = 0
        self.score = 100

    def move_set(self, player):
        eucX = player.rect.x - self.rect.x
        eucY = player.rect.y - self.rect.y 
        euc = math.hypot(eucX, eucY) 
        eucX = eucX/euc
        eucY = eucY/euc #calculate euclidian distance
        self.rect.x += eucX * 2 #move phantom in that direction, scaled by 2.
        self.rect.y += eucY * 2

class Platform(pygame.sprite.Sprite):
    def __init__(self, x, y, length):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("platform.png")
        self.image = pygame.transform.scale(self.image, (length, 10))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)

    def move(self):
        self.rect.y = self.rect.y - 1 #moves up the screen

class Item(pygame.sprite.Sprite):
    def __init__(self, name):
        self.name = name
        self.is_purchased = False 

    def draw(self, x, y, screen):
        name_text = text.render(self.name, True, (0, 0, 0))
        text_but = screen.blit(name_text, (x, y)) #draws the name text to the screen, as this will be used by all items

    def purchased(self):
        self.is_purchased = True #has been purchased

class Triple(Item, pygame.sprite.Sprite):
    
    def __init__(self):
        self.name = "Triple Fire"
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("bullet.png")
        self.image = pygame.transform.scale(self.image, (100, 100))
        self.rect = self.image.get_rect()
        self.rect.center = (0, 0)
        self.is_purchased = False
        super().__init__(self.name)

    def draw(self, x, y, screen):
        self.rect.center = (x, y)
        screen.blit(self.image, (x, y)) #draws the image to the screen
        super().draw(x-30, y+100, screen) #draws the name text

    def purchased(self, gunboots):
        self.is_purchased = True #has been purchased

    def fire(self, isPressed, playerGroup, projectileGroup):
        for player in playerGroup:
            projectile1 = Projectile(player.rect.x-5, player.rect.y+50)
            projectile2 = Projectile(player.rect.x, player.rect.y+50)
            projectile3 = Projectile(player.rect.x+5, player.rect.y+50) #creates three projectiles instead of one
            projectileGroup.add(projectile1)
            projectileGroup.add(projectile2)
            projectileGroup.add(projectile3) 


class powerUp(pygame.sprite.Sprite):
    
    def __init__(self, x, y):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("powerup.png")
        self.image = pygame.transform.scale(self.image, (25, 25))
        self.rect = self.image.get_rect()
        self.rect.center = (x,y)

    def move(self, playerX, playerY):
        label = AI.predict([playerX, playerY])[0] #predicts the position of the player in relation to the powerup
        if label[0] == "L": #if the player is to the left
            self.rect.x += 1 #move to the right 
        if label[0] == "R": #if player to the right
            self.rect.x -= 1 #move left
        if label[1] == "A": #if player above
            self.rect.y -= 0.5 #move up
        if label[1] == "B": #if player below
            self.rect.y += 0.5 #move down. This is because otherwise the player wasn't ever able to catch the powerup
        if self.rect.x > 590: #stops powerup going off the screen
            self.rect.x = 590
        if self.rect.x < 10: #stops powerup going off the screen
            self.rect.x = 10
        if self.rect.x > playerX + 300: #stops powerup going too far right from the enemy
            self.rect.x = playerX + 300
        if self.rect.x < playerX - 300: #stops powerup going too far left of the enemy
            self.rect.x = playerX -300
        if self.rect.y > playerY + 300: #stops powerup going too far below the enemy
            self.rect.y = playerY + 300
        if self.rect.y < playerY - 300: #stops powerup going too far above the enemy
            self.rect.y = playerY - 300

class upgrade_screen():
    
    def __init__(self, screen, backgroundimg, itemGroup):
        self.screen = screen
        self.backgroundimg = backgroundimg
        self.itemGroup = itemGroup

    def loop(self):
        self.screen.blit(self.backgroundimg, (0,0))
        title = text.render("LEVEL COMPLETE", True, (0,0,0))
        self.screen.blit(title, (175, 150))
        rect = (0,0)
        for item in self.itemGroup: #draws all of the items to the screen
            rect = pygame.draw.rect(self.screen, (237, 209, 176), pygame.Rect((190, 345), (200, 150)))
            item.draw(230, 350, screen)
        loop = True
        while loop == True:
            for event in pygame.event.get():
                if event.type == pygame.MOUSEBUTTONDOWN: #if mouse is being clicked
                    mousepos = pygame.mouse.get_pos() #record mouse position
                    for item in self.itemGroup:
                        if rect.collidepoint(mousepos): #if mouse being clicked on one of the items
                            loop = False #go to next level
                            item.purchased(self.itemGroup) #purchase item
            pygame.display.update()

class death_screen():

    def __init__(self, screen, backgroundimg, score):
        self.screen = screen
        self.backgroundimg = backgroundimg
        self.score = score


    def loop(self, restart):
        self.screen.blit(self.backgroundimg, (0,0))
        title = text.render("YOU DIED", True, (0,0,0))
        self.screen.blit(title, (240, 150))
        scoretext = text.render("SCORE: " + str(self.score), True, (0,0,0))
        self.screen.blit(scoretext, (230, 250))
        play = text.render("PLAY AGAIN", True, (0, 0, 0))
        play_but = screen.blit(play, (240, 350))
        ex = text.render("EXIT", True, (0, 0, 0))
        ex_but = screen.blit(ex, (275, 450)) #display menu text
        while True:
            for event in pygame.event.get():
                if event.type == pygame.MOUSEBUTTONDOWN: #if mouse being clicked
                    mousepos = pygame.mouse.get_pos() 
                    if play_but.collidepoint(mousepos): #if clicking on play button 
                        return True #load new level
                    if ex_but.collidepoint(mousepos): #if clicking on exit button
                        pygame.quit() #exit the game
                        quit()
            pygame.display.update()
        return False
        
        
class game_screen():

    def __init__(self, screen, backgroundimg, itemGroup, distance_target, score):
        self.screen = screen
        self.backgroundimg = backgroundimg
        self.itemGroup = itemGroup
        self.distance_target = distance_target
        self.score = score

    def handlePlayer(self, playerGroup, platformGroup, enemyGroup, powerupGroup):
        for player in playerGroup:
            player.move(0, -2) #move the player
            collide = pygame.sprite.spritecollide(player, platformGroup, False) #checks if player is on a platform
            damage = pygame.sprite.spritecollide(player, enemyGroup, False) #checks if player is hitting an enemy
            powerups = pygame.sprite.spritecollide(player, powerupGroup, False) #checks if player is hitting a powerup
            if collide is not None: 
                for sprite in collide: #for any platforms the player is on
                    player.rect.y = sprite.rect.y - 50 #move up the screen with the platform
            if damage is not None:
                for sprite in damage: #for any enemies the player is touching
                    if player.rect.y + 50 <= sprite.rect.y + 20: #if player is landing on enemy
                        self.kill_enemy(sprite, enemyGroup, powerupGroup) #remove the enemy
                        player.move(0, 50) #jump the player up
                    else: #if the enemy is hitting the player
                        sprite.rect.x -= sprite.rect.x - 25 #bounce the enemy back
                        player.health = player.health - 1 #damage the player
            if powerups is not None:
                for power in powerups: #for all the powerups the player is touching
                    powerupGroup.remove(power) #remove the powerup

    def fire(self, isPressed, gunboots, playerGroup, projectileGroup):
        if isPressed: #if firing
            for gun in gunboots:
                if gun.is_purchased == True: #the currently equipped weapon
                    gun.fire(isPressed, playerGroup, projectileGroup) #fire
            for player in playerGroup: #if default weapon
                projectile = Projectile(player.rect.x, player.rect.y+50) #create projectile below the player
                projectileGroup.add(projectile)

    def pause_menu(self, screen, click, mousepos):
        pygame.draw.rect(screen, (237, 209, 176), pygame.Rect((100, 300), (400, 200)))
        paused = text.render("PAUSED", True, (0, 0, 0))
        screen.blit(paused, (250, 300))
        cont = text.render("CONTINUE", True, (0, 0, 0))
        cont_but = screen.blit(cont, (235, 350))
        options = text.render("OPTIONS", True, (0, 0, 0))
        opt_but = screen.blit(options, (242, 400))
        ex = text.render("EXIT", True, (0, 0, 0))
        ex_but = screen.blit(ex, (265, 450)) #display menu text
        if click:
            if cont_but.collidepoint(mousepos): #if clicking the continue button
                return False #close the menu
            if opt_but.collidepoint(mousepos): #if clicking the options button
                return True #do nothing for now
            if ex_but.collidepoint(mousepos): #if clicking the exit button
                pygame.quit() #quit the game
                quit()
        return True


    def shop_menu(self, screen, click, mousepos, itemGroup):
        pygame.draw.rect(screen, (237, 209, 176), pygame.Rect((50, 200), (500, 400)))
        shop = text.render("SHOP", True, (0, 0, 0))
        screen.blit(shop, (270, 220))#draw the menu and text
        for item in itemGroup: #draw the items
            item.draw(230, 350, screen)

    def kill_enemy(self, enemy, enemyGroup, powerupGroup):
        self.score += enemy.score #update the player's score
        enemyGroup.remove(enemy) #remove the enemy
        if random.randint(1, 10) == 5: #calculate if a powerup will be created or not
            powerup = powerUp(enemy.rect.x, enemy.rect.y)
            powerupGroup.add(powerup)
        

    def main_loop(self):
        clock = pygame.time.Clock()
        height = backgroundimg.get_height()
        x=0
        y=0 
        x1=0
        y1=height #starting values for the two background images
        playerGroup = pygame.sprite.Group()
        enemyGroup = pygame.sprite.Group()
        platformGroup = pygame.sprite.Group()
        projectileGroup = pygame.sprite.Group()
        powerupGroup = pygame.sprite.Group() #sprite groups
        player = Player(300,0) #create the player
        playerGroup.add(player)
        running = True #run the loop
        paused = False
        shop = False
        mousepos = (0, 0)
        distance = 0 #distance travelled
        while running:
            if player.health < 1: #if player is dead
                death = death_screen(self.screen, self.backgroundimg, self.score)
                return death.loop(self) #load the deeath screen
            else:
                click = False
                pressed = pygame.key.get_pressed() #get if the player is clicking a key
                for event in pygame.event.get():
                    if event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_ESCAPE and not shop: #if pressing escape and not in the shop menu
                            if paused: #if already paused
                                paused = False #close the pause menu
                            else: #if not paused
                                paused = True #load the pause menu
                        if event.key == pygame.K_SPACE and not paused: #if pressing space and not paused
                            if shop: #if in the shop
                                shop = False #close the shop
                            else: #if not in the shop
                                shop = True #load the shop
                        if not paused or not shop: 
                            if event.type == pygame.QUIT: #if player closes the game
                                running = False #stop the loop
                            if event.type == pygame.KEYDOWN: #if pressing a key
                                if event.key == pygame.K_UP: #if pressing up key
                                    for player in playerGroup:
                                        player.move(0, 50) #jump the player up
                                if event.key == pygame.K_DOWN: #if pressing down key
                                    self.fire(True, self.itemGroup, playerGroup, projectileGroup) #fire a projectile
                    if event.type == pygame.MOUSEBUTTONDOWN: #if clicking
                        mousepos = pygame.mouse.get_pos() #get where the mouse is being clicked
                        click = True

                if not paused and not shop:         
                    if pressed[pygame.K_LEFT]: #if pressing/holding left
                        for player in playerGroup:
                            player.move(-5, 0) #move the player left
                    if pressed[pygame.K_RIGHT]: #if pressing/holding right
                        for player in playerGroup: 
                            player.move(5, 0)#move the player right

                    for enemy in enemyGroup:
                        enemy.move_set(playerGroup.sprites()[0]) #move all of the enemies

                    for new_platform in platformGroup:
                        new_platform.move() #move all of the platforms

                    for projectile in projectileGroup:
                        projectile.move() #move all of the projectiles
                        for enemy in enemyGroup:
                            if projectile.rect.colliderect(enemy.rect): #for all projectiles touching enemies
                                projectileGroup.remove(projectile) #remove the projectile
                                self.kill_enemy(enemy, enemyGroup, powerupGroup) #remove the enemy
                            if projectile.rect.y > 800: #if projectile off the screen
                                projectileGroup.remove(projectile) #remove the projectile

                    for powerup in powerupGroup:
                        for player in playerGroup: 
                            powerup.move(player.rect.x, player.rect.y) #move all of the powerups

                    platform_target = random.randint(1,100) #decide whether to create a new platform
                    if platform_target == 1:
                        platform = Platform(random.randint(20, 380), 800, random.randint(50, 150)) #create new platform of random length at random x value
                        platformGroup.add(platform)

                    enemy_target = random.randint(1,200) #decide whether to create a new enemy
                    if enemy_target == 1:
                        enemy_choice = random.randint(1,2) #decide what enemy to create
                        if enemy_choice == 1:
                            ghost = Ghost(random.randint(20, 380), 800) #add new ghost at random x value
                            enemyGroup.add(ghost)
                        if enemy_choice == 2:
                            phantom = Phantom(random.randint(20,380), 800) #add new phantom at random x value
                            enemyGroup.add(phantom)
                    
                    self.handlePlayer(playerGroup, platformGroup, enemyGroup, powerupGroup) #move player, handle player collisions
                    
                    y-=1
                    y1-=1
                    screen.blit(backgroundimg, (x,y)) #move the first background image
                    screen.blit(backgroundimg, (x1,y1)) #move the second background image
                    screen.blit(text.render("Score: " + str(self.score), True, (0, 0, 0)), (0, 0)) #draw the score
                    screen.blit(text.render("Lives: " + str(player.health), True, (0, 0, 0)), (450, 0)) #draw the lives

                    if y <= -height: #if background reaches the top 
                        y = height #move it to the bottom
                        distance += 1
                    if y1 <= -height: #if other background reaches the top
                        y1 = height #move it to the bottom
                        distance += 1

                    if distance >= self.distance_target: #if level is complete
                        up_screen = upgrade_screen(self.screen, self.backgroundimg, self.itemGroup) #load the upgrade screen
                        up_screen.loop()
                        running = False #end this level loop
                        
                    else:
                        playerGroup.update()
                        enemyGroup.update()
                        platformGroup.update()
                        projectileGroup.update()
                        powerupGroup.update()
                        playerGroup.draw(self.screen)
                        enemyGroup.draw(self.screen)
                        platformGroup.draw(self.screen)
                        projectileGroup.draw(self.screen)
                        powerupGroup.draw(self.screen)
                        pygame.display.update() #update the screen display
                        clock.tick(60)
                else:
                    if paused: 
                        paused = self.pause_menu(self.screen, click, mousepos) #load the pause menu
                        pygame.display.update()
                    if shop:
                        self.shop_menu(self.screen, click, mousepos, self.itemGroup) #load the shop menu
                        pygame.display.update()

class main_menu():

    def __init__(self, screen, backgroundimg):
        self.screen = screen
        self.backgroundimg = backgroundimg

    def loop(self):
        self.screen.blit(self.backgroundimg, (0,0))
        title = text.render("HAVE A NICE TRIP", True, (0,0,0))
        self.screen.blit(title, (175, 150))
        play = text.render("PLAY", True, (0,0,0))
        play_but = self.screen.blit(play, (265, 300))
        options = text.render("OPTIONS", True, (0,0,0))
        opt_but = self.screen.blit(options, (245, 400))
        ext = text.render("EXIT", True, (0,0,0))
        ext_but = self.screen.blit(ext, (265, 500)) #draw the menu text
        main = True
        score = 0
        while main == True:
            for event in pygame.event.get():
                if event.type == pygame.MOUSEBUTTONDOWN: #if clicking the mouse
                    mousepos = pygame.mouse.get_pos() #get cursor position
                    if play_but.collidepoint(mousepos): #if clicking the play button
                        running = True
                        x = 1
                        triple = Triple()
                        itemGroup = []
                        itemGroup.append(triple)
                        while running == True: #loops so can start new level once old level is complete
                            game = game_screen(self.screen, self.backgroundimg, itemGroup, x, score) #load new level
                            restart = game.main_loop() #run new level
                            x += 1 #increase the length of the next level
                        if restart: #if player wants to play again
                            self.loop() #load new main menu
                    if opt_but.collidepoint(mousepos):
                        pass
                    if ext_but.collidepoint(mousepos): #if player clicks quit
                        pygame.quit() #exit the game
                        quit()
            pygame.display.update()

main_menu = main_menu(screen, backgroundimg) #comment out these two lines if running the test files
main_menu.loop()
