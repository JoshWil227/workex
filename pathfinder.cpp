//auth Joshua Wilcock
//For code explanation see summary in readme
#include <iostream>
#include <fstream>
#include <queue>
#include <string>
using namespace std;

string line = "";
string grid[25][9];
bool visited[25][9];
bool end = false;
int x = 0, y = 0, xMax = 0;
int startX = 0, startY = 0;
int aCount = 0, bCount = 0;
string path = "";
int dirX[4] = {1, -1, 0, 0};
int dirY[4] = {0, 0, 1, -1};

struct Node{
       int xVal;
       int yVal; 
       string direction;
       struct Node *parent;
};

queue<Node*> nodeQueue;

void findNeighbours(Node * parentNode){
     for(int l = 0; l < 4; l++){
             int curX, curY;
             curX = parentNode->xVal + dirX[l];
             curY = parentNode->yVal + dirY[l];
             
             if(curX >= 0 && curY >= 0 && curX <= xMax && curY <= y){
                     if(!visited[curX][curY] && grid[curX][curY] != "x"){
                                             Node * curNode = NULL;
                                             curNode = new Node();
                                             curNode->xVal = curX;
                                             curNode->yVal = curY;
                                             visited[curX][curY] = true;
                                             curNode->parent = parentNode;
                                             
                                             if(parentNode->xVal < curNode->xVal){
                                                  curNode->direction = "E";
                                             }
                                             if(parentNode->xVal > curNode->xVal){
                                                  curNode->direction = "W";
                                             }
                                             if(parentNode->yVal > curNode->yVal){
                                                  curNode->direction = "N";
                                             }
                                             if(parentNode->yVal < curNode->yVal){
                                                  curNode->direction = "S";
                                             }
                                             nodeQueue.push(curNode);
                     }
             }
     }
     return;
}

int main(){
    cout << "Please enter the file name: ";
    string filename;
    cin >> filename;
    Node * head = NULL;
    Node * tail = NULL;
    ifstream myfile(filename.c_str());
    if(myfile){
               while(getline(myfile, line)){
                                     xMax = x;
                                     x = 0;
                                     for(int i = 0; i < line.length(); i++){
                                             grid[i][y] = line[i];
                                             if(line[i] == 'A'){
                                                         aCount++;
                                                         head = new Node();
                                                         head->xVal = x;
                                                         head->yVal = y;
                                                         visited[i][y] = true;
                                                         head->direction = "";
                                                         head->parent = NULL;
                                                         nodeQueue.push(head);
                                             }
                                             if(line[i] == 'B'){
                                                         bCount++;
                                                         tail = new Node();
                                             }
                                             x++;
                                     }
                                     y++;
               }
    }
    else{
         cout << "No file found.\n";
         system("pause");
         return 0;
    }
    if(aCount != 1){
               cout << "Invalid grid, please include a start point (A) \n";
               system("pause");
               return 0;
    }
    if(bCount != 1){
              cout << "Invalid grid, please include an exit (B) \n";
              system("pause");
              return 0;
    }
    while(nodeQueue.size() > 0){
                Node * curNode = NULL;
                curNode = nodeQueue.front();
                nodeQueue.pop();
                if(grid[curNode->xVal][curNode->yVal] == "B"){
                              end = true;
                              tail = curNode;
                              break;
                }
                findNeighbours(curNode);
    }
    if(end){            
            Node * node = NULL;
            node = tail;
            while(node != NULL){
                       path = path + node->direction;
                       node = node->parent;
            }
            reverse(path.begin(), path.end());
            cout << path << "\n";
    }
    else{
         cout << "Invalid input, no path to exit\n";    
         system("pause");
         return 0;
    }
    system("pause");
    return 0;                 
}
                                            
                                              
