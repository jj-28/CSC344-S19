#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Ins{
int write_val;
int move_direction;
int new_state;
};


struct Node {
int val;

struct Node *next;
struct Node *previous;
};

struct Node * h;

struct Node * current;
int blank_state=  66;

struct Node * make_node(int c) 
{
struct Node* n1 = (struct Node*) malloc (sizeof(struct Node));
n1->val = c;
n1->next = NULL;
n1->previous= NULL;
return n1;
}
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

struct Node* n2 = make_node(blank_state);
current->previous = n2;
n2->next= current;
current= current->previous;
return;
}

void cptr_right () {

if (current->next != NULL) {
current= current->next;
return;
}

struct Node* n2 = make_node(blank_state);
current->next = n2;
n2->previous= current;
current= current->next;
return;
}

void printdList () {
struct Node * r  = h;
while (r != NULL) {
char c = r->val;
printf("%c ",c);
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
int line_count=0;
int x = 0;
FILE *fp;
printf("%s", "Input file: ");
scanf("%s", input_str);
fp = fopen(input_str, "r");
if (fp) {
while (fgets(buffer, sizeof buffer, fp)) {
list[x] = strdup(buffer);
x++;
line_count++;
}
strcpy(init_tape, list[0]);
num_of_states = atoi(list[1]);
start_state = atoi(list[2]);
end_state = atoi(list[3]);
for (int i = 0; init_tape[i] != '\r'; i++) {
append((int) (init_tape[i]) - 0);
}
int total = num_of_states * 255;
struct Ins **ins_table = malloc(total * sizeof(struct Ins *));
for (int i = 0; i < total; i++) {
ins_table[i] = malloc(sizeof(struct Ins));
}
int inc = 4;
int ascii_col;
while (inc < line_count) {
row_num = (list[inc][0] - '0');
ascii_col = (int) list[inc][2];
ins_table[row_num][ascii_col].write_val = (int) list[inc][4];
ins_table[row_num][ascii_col].move_direction = (int) list[inc][6];
ins_table[row_num][ascii_col].new_state = (list[inc][8] - '0');
inc++;
}
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
struct Node * r  = h;
while (r != NULL) {
char c = r->val;
printf("%c ",c);
r= r->next;
} else {
printf("%s", "file name not valid");
}
return 0;
}