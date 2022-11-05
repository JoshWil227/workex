import pygame

pygame.init()
screen_height = 800
screen_width = 600
screen = pygame.display.set_mode([screen_width, screen_height])
backgroundimg = pygame.image.load("redbricks.jpg")

screen.blit(backgroundimg, (0,0))
pygame.display.update()


def main_loop(screen, backgroundimg):
    clock = pygame.time.Clock()
    height = backgroundimg.get_height()
    x=0
    y=0
    x1=0
    y1=-height
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        y-=1
        y1-=1
        screen.blit(backgroundimg, (x,y))
        screen.blit(backgroundimg, (x1,y1))
        if y > height:
            y = -height
        if y1 > height:
            y = -height
        pygame.display.update()
        clock.tick(60)
    pygame.quit()
    quit()

main_loop(screen, backgroundimg)
