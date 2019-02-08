//
// Created by Jeffrey on 2/8/2019.
//
#include <stdio.h>

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
do {
    struct Item i1;
    int x_coord;
    int y_coord;
    printf("List the item information: Name, Price and Location (X, Y), separated by one space. \n Type /Q when finished. \n");
    scanf(" %s, %d, %d, %d", i1.name, i1.price, x_coord, y_coord);




    printf("Item %s with price $%d was added at Row %d , Column %d. \n", i1.price, i1.name, x_coord, y_coord);
    printf("(Continue? (C) | Quit? (Q)");

    scanf("%c", choice);

} while (choice == "c");



return 0;
}


