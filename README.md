# NoobLocker
Prevents unwanted actions of players (grief)
 
## Permissions
* nooblocker.bypass - removes all restrictions
* nooblocker.griefer - imposes restrictions always

## Config file
```yml
####################################################
#                                                  #
#                |   NOOB_LOCKER  |                #
#                                                  #
####################################################

#Contact me:
#
#VK: vk.com/deelter
#TWITTER: twitter.com/roma200315
#YOUTUBE: youtube.com/channel/UCRACU2JfOfClrlEHImv5pYw
#GITHUB: github.com/DeelTer
#
#DISCORD: DeelTer#4782

# До тех пор, пока игрок не наиграет в онлайне
# определённое время (указывается ниже) - ему будут запрещены
# указанные в 'actions' взаимодействия
noob-time:
  minutes: 5
  days: 0

# Нужно ли проверять указанное взаимодействие
# При true будут происходить проверки
actions:
  place-tnt: true
  place-crystal: true
  place-nether-bed: true
  explode-nether_bed: true
  # Взрыв кристалла энда
  explode-crystal: true
  # Использование зажигалки
  use-flind-and-steel: true
  # Использование ведра лавы
  use-lava-bucket: true
  kill-villagers: true
  damage-players: true

restricted-areas:
  ad-hub:
    radius: 120
    location: "world_nether,0,0,0"
  spawn:
    radius: 120
    location: "world,0,0,0"

messages:
  warn: "Вы не можеет сделать это"
  ```
