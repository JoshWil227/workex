import pytest, pygame, enemies, math

#tested platforms by having the player drop onto them - no longer phases through
# and now moves up the screen with them. Slight bug with how the collide function
# works meaning that you can ride up on top of them again if you move into
# the side of them.

# Tested the enemies by having the player drop onto them - no longer phases
#through and now deletes the enemies. If the enemy hits the player rather than
#the player hitting the enemy, the player loses health.

screen = pygame.display.set_mode([800, 600])
background_img = ""
itemGroup = []
gamescreen = enemies.game_screen(screen, background_img, itemGroup, 1, 0)
playerGroup = pygame.sprite.Group()
projectileGroup = pygame.sprite.Group()
platformGroup = pygame.sprite.Group()
enemyGroup = pygame.sprite.Group()
powerupGroup = pygame.sprite.Group()
player = enemies.Player(300, 150)
playerGroup.add(player)

def test_Fire():
    gamescreen.fire(True, [], playerGroup, projectileGroup)
    list = projectileGroup.sprites()
    assert len(list) == 1

test_Fire()

#test_Fire() tests whether or not the fire method creates a projectlie

projectileGroup.empty() #empties the projectile list between tests

def test_Fire_Move():
    for i in range(5):
        for projectile in projectileGroup:
            projectile.move()
    for projectile in projectileGroup:
        assert projectile.rect.y == (player.rect.y + 50) + 50
        #50 for the spawn point and 50 for the movement

test_Fire_Move()
 #test_Fire_Move tests that the projectiles are moving down the screen

projectileGroup.empty() #empties the projectile list between tests
phantom = enemies.Phantom(50, 800)
enemyGroup.add(phantom)

def test_Add_Phantom():
    list = enemyGroup.sprites()
    assert len(list) == 1

test_Add_Phantom() #tests that the phantom is implemented correctly

def test_Phantom_Move():
    eucX = player.rect.x - phantom.rect.x
    eucY = player.rect.y - phantom.rect.y
    euc1 = math.hypot(eucX, eucY) #calculates the distance between the player and the enemy
    phantom.move_set(player) #moves the enemy towards the player
    eucX = player.rect.x - phantom.rect.x
    eucY = player.rect.y - phantom.rect.y
    euc2 = math.hypot(eucX, eucY) #calculates the distance between the player and the enemy
    assert euc2 < euc1

test_Phantom_Move() #tests that the phantom moves closer

#tested the pause menu manually. tested that it can be opened and closed using the esape key
#tested that clicking continue closes the pause menu
#tested that clicking options does not do anything yet
#tested that clicking exit closes the game
#tested that the game does not continue in the background while the game is paused

#tested the shop menu manually. tested that it can be opened and closed using the space bar and does not open when the pause menu is open
#tested that the shop menu displays an item image and item name correctly
#tested that clicking the upgrade applies the upgrade.

#tested the main menu screen by ensuring just the background appeared first.
#then tested by ensuring the title and menu text appeared on screen.
#retested several times to get the text as close to centre-aligned as possible
#then tested by ensuring that clicking on the play button started the game and the exit button ended the program.

#tested the end of level screen by ensuring that once the player had reached a certain distance, the screen appears.
#tested and retested several times to ensure that the box around the upgrade and it's name is sized correctly
#tested to ensure that clicking the upgrade ends the game, because there are not yet more levels in place.

#tested that the clicking the upgrade now starts a new level, twice as long as the first level. Discovered problem with how sprites are spawned,
#with lots being spawned at the start of the level in a backlog due to the clock ticking. Another problem was that the upgrades do not carry accross levels.

#tested that the upgrades now carry accross levels.

def test_Kill_Enemies():
    enemy = enemies.Ghost(player.rect.x, player.rect.y + 60)
    enemyGroup.add(enemy)
    gamescreen.fire(True, [], playerGroup, projectileGroup) #fire a projectile
    en = enemyGroup.sprites()
    en_num = len(en)
    proj = projectileGroup.sprites()
    proj_num = len(proj) #count the number of enemies and projectiles 
    for i in range(5):
        for projectile in projectileGroup:
            projectile.move()
            for enemy in enemyGroup:
                if projectile.rect.colliderect(enemy.rect):
                    projectileGroup.remove(projectile)
                    enemyGroup.remove(enemy) #the code in the game loop to handle projectile collisions
    en = enemyGroup.sprites()
    en_num_2 = len(en)
    proj = projectileGroup.sprites()
    proj_num_2 = len(proj) #recount the number of enemies and projectiles
    assert en_num_2 == en_num -1 and proj_num_2 == proj_num - 1

test_Kill_Enemies()

def test_Score():
    enemy = enemies.Ghost(player.rect.x, player.rect.y + 60)
    enemyGroup.add(enemy)
    gamescreen.fire(True, [], playerGroup, projectileGroup) #fire a projectile
    for i in range(5):
        for projectile in projectileGroup:
            projectile.move()
            for enemy in enemyGroup:
                if projectile.rect.colliderect(enemy.rect):
                    projectileGroup.remove(projectile)
                    gamescreen.kill_enemy(enemy, enemyGroup, powerupGroup)
    assert gamescreen.score == 50
    enemy = enemies.Ghost(player.rect.x, player.rect.y + 60)
    enemyGroup.add(enemy)
    for i in range(5):
        gamescreen.handlePlayer(playerGroup, platformGroup, enemyGroup, powerupGroup) #moves the player and detects and handles any collisions
    assert gamescreen.score == 100 #score should have increased because another enemy has been killed

test_Score()

#tested that the AI predicted the labels of different co-ordinates
#tested that the sprite for the powerup appears
#tested that the sprite avoids the player. however, it always just disappears off of the bottom of the screen. therefore, a measure must be introduced to stop this -
#either a limit as to how far below the player it can go, or how many times a prediction can be made in a row before it is overriden by something to make the movement more interesting.
#tested a new system for the powerup which keeps it within a certain distance of the player.

def test_Powerup_Collision():
    powerup = enemies.powerUp(player.rect.x, player.rect.y)
    powerupGroup.add(powerup)
    list1 = powerupGroup.sprites()
    gamescreen.handlePlayer(playerGroup, platformGroup, enemyGroup, powerupGroup)
    list2 = powerupGroup.sprites()
    assert list2 == list1 - 1

#tested that the death screen appeared after a certain number of lives had been lost
#tested that the text appeared correctly and was alligned correctly
#tested that the exit button exits the game
#tested that the play again button starts a new game

#tested that the new way of determining when enemies and platforms are generated works
#tested different frequencies for generation (one platform per second, one enemy every 5 seconds, one platform every 1.5 seocnds one enemy every 4.2 seconds)
#tested new movement range for the ghost 

print("All Passed")
    
