//
// Created by Jeffrey on 2/8/2019.
//
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

struct Item {
    char name [50];
    int price;

};

int main () {
    int num_of_shelves ;
    char nums[5];
    int num_of_slots;
    struct Item i1;
    struct Item * i_ptr = &i1;

    printf("List number of shelves in the unit: \n");
    scanf("%d", &num_of_shelves);


    printf("List number of slots on each shelf : \n");
    scanf("%d", &num_of_slots);
    char choice;

    //allocates memory for all of the items in the space
    int total = num_of_shelves * num_of_slots;
    struct Item ** unit_1 = malloc(total * sizeof(struct Item *)) ;
    for (int i = 0; i < total; i++) {
        unit_1[i] = malloc(sizeof(struct Item));
    }

    do {
        int x_coord;
        int y_coord;
        printf("List item Row:\n");
        scanf("%d", &x_coord);
        printf("List item Column:\n");
        scanf("%d", &y_coord);

        printf("List item Name:\n");
        scanf(" %s", i_ptr-> name);
        printf("List item price:\n");
        scanf("%d", i_ptr-> price);
        printf("%s", i_ptr->name);
        strcpy(unit_1 [x_coord] [y_coord].name, i1.name);
        unit_1 [x_coord] [y_coord].price = i1.price;
        printf("Item %s with price $%d was added at Row %d , Column %d. \n",  unit_1[x_coord] [y_coord].name,
                unit_1 [x_coord] [y_coord].price,  &x_coord, &y_coord);
        printf("(Continue? (C) | Quit? (Q) \n");
        scanf("%c", &choice);

    } while (strcmp("c", choice)==0 );

//for (int i =0 ; i < num_of_shelves; i++) {
//    for (int j = 0; j < num_of_slots; j++) {
//        printf("Item %s with price $%d was added at Row %d , Column %d. \n", unit_1[i][j]->name, unit_1[i][j]->price);
//    }
//}
    printf("done")
            ;
    return 0;
}


