#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
* Instruction table for the program.
* In a single cell, it contains: The value to be written, the direction (L,R), and the new state
*/
struct Ins{
    int write_val;
    int move_direction;
    int new_state;
};

/* Basic Structure for  the tape, builds the linked list
 *
 */
struct Node {
    int val;
//    char * val ;
    struct Node *next;
    struct Node *previous;
};
//Pointer Representing the head node
struct Node * h;
//Pointer representing the current node being read
struct Node * current;


int blank_state=  66;

/*pass in a reference to the head node's mem address (next and previous)
takes care of inserting node at front of list, and back of list
 */

//take in a double pointer an an int


struct Node * make_node(int c) {
    struct Node* n1 = (struct Node*) malloc (sizeof(struct Node));
    n1->val = c;
    n1->next = NULL;
    n1->previous= NULL;
    return n1;
}
//Add nodes to the back of the list
void append (int c) {
    struct Node* a_ptr= h;
    current=h;
    struct Node* n2 = make_node(c);
    if (a_ptr == NULL) {
        h=n2;
        return;
    }

    while(a_ptr->next != NULL) {
        a_ptr= a_ptr->next;
    }
    n2->previous = a_ptr;
    a_ptr->next = n2;
}
//Add nodes to the back of the list
void prepend(int c) {
    struct Node* n2 = make_node(c);
    if (h == NULL) {
        h= n2;
        return;
    }
    h->previous = n2;
    n2-> next = h;
    h= n2;
}
void cptr_left () {
    if (current->previous != NULL) {
        current= current->previous;
        return;
    }
    prepend(blank_state);
    struct Node* n2 = make_node(blank_state);
    current->previous = n2;
    n2->next= current;
    current= current->previous;
    return;

}
//moves the current pointer to the l
void cptr_right () {
//    int blank_state= 66;
    if (current->next != NULL) {
        current= current->next;
        return;
    }
//    append(blank_state);
//    current= current->next;
    struct Node* n2 = make_node(blank_state);
    current->next = n2;
    n2->previous= current;
    current= current->next;
    return;
}

//Prints all of the nodes in the LL
void printdList () {
    struct Node * r  = h;
    while (r != NULL) {
    char c = r->val;
    printf("%c ",c);
//        printf("%d ",(r->val -0) );
        r= r->next;

    }
}





int main () {
    char input_str[100];
    char buffer [1000];
    char * list [1000];
    char init_tape [50];
    int num_of_states;
    int current_state;
    int start_state;
    int end_state;
    //counts how many lines are in the file
    int line_count=0;
    //array containing the raw instructions
    char ins_7 [20];
    char testing [20];
    int x = 0;
    int len;
    FILE *fp;
    printf("%s", "Input file: ");
    scanf("%s", input_str);
    fp = fopen(input_str, "r");
    if (fp) {
        while (fgets(buffer, sizeof buffer, fp)) {
            list[x] = strdup(buffer);
//            strcpy(list[x], buffer);
            x++;
            line_count++;
        }
        //setting list string
        strcpy(init_tape, list[0]);
//        len = strlen(input_str);
        num_of_states = atoi(list[1]);
        start_state = atoi(list[2]);
        end_state = atoi(list[3]);

        for (int i = 0; init_tape[i] != '\r'; i++) {
//            printf("%d \n", init_tape[i]);
            append((int) (init_tape[i]) - 0);
        }
//        printdList();

        int total = num_of_states * 256;
        struct Ins **ins_table = malloc(total * sizeof(struct Ins *));

        for (int i = 0; i < total; i++) {
            ins_table[i] = malloc(sizeof(struct Ins));
        }

        int inc = 4;
        // inc represents the first tape command
        /*
         48 = 0;
         49 = 1;
         66 = 'B';
         82 = 'R';
         76 = 'L';
         */
//        printf("%d", (list[7][0]- '0'));
        int row_num;
        int ascii_col;
        while (inc < line_count) {
            //populating the instruction table with instructions from the input file
            row_num= (list[inc][0] - '0');
            ascii_col = (int) list[inc][2];
//            int wv =ins_table[row_num][ascii_col].write_val;
            ins_table[row_num][ascii_col].write_val = (int) list[inc][4];
//            int md= ins_table[row_num][ascii_col].move_direction;
            ins_table[row_num][ascii_col].move_direction= (int) list[inc][6];
//            int ns =ins_table[row_num][ascii_col].new_state;
            ins_table[row_num][ascii_col].new_state = (list[inc][8]- '0');
            inc++;
        }
//        printf("%s", "all good");
//        /*
        current_state = start_state;

        int list_val;
        int move_dir;
        int nv;

        while (current_state != end_state) {
            list_val = current->val;
            nv= ins_table[current_state][list_val].write_val;
            current->val =nv;
            move_dir = ins_table[current_state][list_val].move_direction;
            if (move_dir == 76) {
                cptr_left();
                current_state = ins_table[current_state][list_val].new_state;
            } else {
                cptr_right();
                current_state = ins_table[current_state][list_val].new_state;
            }
        }
        printdList();
        for (int i = 0; i < total; i++) {
            free(ins_table[i]);
        }


    } else {
        printf("%s", "file name not valid");
    }
    return 0;
}