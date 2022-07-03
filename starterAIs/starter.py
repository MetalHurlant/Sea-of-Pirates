from collections import namedtuple

Player = namedtuple('Player', [
    'id', 'x', 'y', 'rotation', 'sail_angle', 'life'
])

player_count = int(input())
my_id = int(input())
for i in range(player_count):
    player_id = int(input())
    x, y, rotation, sail_angle, life = [int(i) for i in input().split()]

    player = Player(
        player_id, x, y, rotation, sail_angle, life
    )


# game loop
while True:
    player_count = int(input())
    my_id = int(input())
    for i in range(player_count):
        player_id = int(input())
        x, y, rotation, sail_angle, life = [int(i) for i in input().split()]

        player = Player(
            player_id, x, y, rotation, sail_angle, life
        )
    print("WAIT")
