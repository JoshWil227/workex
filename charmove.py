import pygame

pygame.init()
screen_height = 800
screen_width = 600
screen = pygame.display.set_mode([screen_width, screen_height])
backgroundimg = pygame.image.load("redbricks.jpg")

screen.blit(backgroundimg, (0,0))
pygame.display.update()


class Player(pygame.sprite.Sprite):
    def __init__(self, x, y):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load("stickman.png")
        self.image = pygame.transform.scale(self.image, (30,50))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)

def main_loop(screen, backgroundimg):
    clock = pygame.time.Clock()
    height = backgroundimg.get_height()
    x=0
    y=0
    x1=0
    y1=-height
    playerGroup = pygame.sprite.Group()
    playerGroup.add(Player(300, 600))
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:
                    for player in playerGroup:
                        player.rect.y -= 10
                        
        pressed = pygame.key.get_pressed()
        if pressed[pygame.K_LEFT]:
            for player in playerGroup:
                player.rect.x-=1
        if pressed[pygame.K_RIGHT]:
            for player in playerGroup:
                player.rect.x+=1
        y-=1
        y1-=1
        screen.blit(backgroundimg, (x,y))
        screen.blit(backgroundimg, (x1,y1))
        if y > height:
            y = -height
        if y1 > height:
            y = -height
        playerGroup.update()
        playerGroup.draw(screen)
        pygame.display.update()
        clock.tick(60)
    pygame.quit()
    quit()

main_loop(screen, backgroundimg)
