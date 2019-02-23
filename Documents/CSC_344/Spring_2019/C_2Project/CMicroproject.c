////
//// Created by Jeffrey Johnson (804844514) on 2/8/2019.
////
//#include <stdio.h>
//#include <stdlib.h>
//#include <memory.h>
//
//struct Item {
//    char name [50];
//    int price;
//
//};
//
//int main () {
//    int num_of_shelves ;
//    char nums[5];
//    int num_of_slots;
//    struct Item i1;
//    struct Item * i_ptr = &i1;
////    char look up
//
//    printf("List number of shelves in the unit: \n");
//    scanf("%d", &num_of_shelves);
//
//
//    printf("List number of slots on each shelf : \n");
//    scanf("%d", &num_of_slots);
//    char choice [1];
//
//    //allocates memory for all of the items in the space
//    int total = num_of_shelves * num_of_slots;
//    struct Item ** unit_1 = malloc(total * sizeof(struct Item *)) ;
//    for (int i = 0; i < total; i++) {
//        unit_1[i] = malloc(sizeof(struct Item));
//    }
//
//    do {
//        int x_coord;
//        int y_coord;
//        printf("List item Row:\n");
//        scanf("%d", &x_coord);
//        printf("List item Column:\n");
//        scanf("%d", &y_coord);
//
//        printf("List item Name:\n");
//        scanf(" %s", i_ptr-> name);
//        printf("List item price:\n");
//        scanf("%d", &i_ptr-> price);
//        //breaks here
//        strcpy(unit_1 [x_coord] [y_coord].name, i1.name);
//        unit_1 [x_coord] [y_coord].price = i1.price;
//        printf("Item %s with price $%d was added at Row %d , Column %d. \n",  unit_1[x_coord] [y_coord].name,
//                unit_1[x_coord] [y_coord].price,  x_coord, y_coord);
//        printf("(Continue? (C) | Quit? (Q) \n");
//        scanf("%s", choice);
//
//    } while (strcmp("C", choice)==0 );
//    printf("=========LOOKUP========= \n");
//    do {
//        int x;
//        int y;
//        printf("Enter the x coordinate of the item you want to look up: \n");
//        scanf("%d", &x);
//        printf("Enter the y coordinate of the item you want to look up: \n");
//        scanf("%d", &y);
//        printf("Item %s with price $%d was added at Row %d , Column %d. \n",  unit_1[x] [y].name,
//               unit_1[x] [y].price,  x, y);
//    printf("(Continue? (C) | Quit? (Q) \n");
//    scanf("%s", choice);
//    } while (strcmp("C", choice)==0 );
//
//    printf("Program Finished");
//            ;
//    return 0;
//}
//
//
