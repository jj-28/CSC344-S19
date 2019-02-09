//
// Created by Jeffrey on 2/8/2019.
//
#include <stdio.h>
#include <stdlib.h>

struct Item {
    char name [50];
    int price;

};

int main () {
    int num_of_shelves;
    int num_of_slots;


    printf("List number of shelves in the unit: \n");
    scanf("%d", num_of_shelves);

    printf("\n List number of slots on each shelf : \n");
    scanf("%d", num_of_shelves);

    char choice;
    struct Item ** unit_1 [num_of_shelves] [num_of_slots];
    for (int i =0 ; i < num_of_shelves; i++) {
        for (int j = 0; j < num_of_slots; j++) {
            unit_1 [i][j] = malloc(sizeof(struct Item *) );
        }
    }
    do {
        struct Item i1;

        int x_coord;
        int y_coord;
        printf("List the item information: Name, Price and Location (X, Y), separated by one space. \n Type /Q when finished. \n");
        scanf(" %s, %d, %d, %d", i1.name, i1.price, x_coord, y_coord);
        struct Item * i_ptr = &i1;
        unit_1 [x_coord] [y_coord] = &i_ptr;
        printf("Item %s with price $%d was added at Row %d , Column %d. \n", i1.price, i1.name, x_coord, y_coord);
        printf("(Continue? (C) | Quit? (Q)");

        scanf("%c", choice);

    } while (choice == "c");

//for (int i =0 ; i < num_of_shelves; i++) {
//    for (int j = 0; j < num_of_slots; j++) {
//        printf("Item %s with price $%d was added at Row %d , Column %d. \n", unit_1[i][j]->name, unit_1[i][j]->price);
//    }
//}
    printf("done")
            ;
    return 0;
}


