#Josh Wilcock
#Using functions from sci kit learn

from sklearn.neighbors import KNeighborsClassifier
import numpy as np

class ai_model():
    def __init__(self):
        self.x = np.array([[20,50], [50, -74], [-30, 27], [-100, -100], [100, 49], [-100, 89], [72, -65], [3, 3], [-5, 10], [8, -5], [-1, -4], [290, 140], [-234, 220], [188, -196], [-242, -179]])
        self.y = ["RB", "RA", "LB", "LA", "RB", "LB", "RA", "RB", "LB", "RA", "LA", "RB", "LB", "RA", "LA"] #R is Right, L is Left, A is Above, B is Below, referring to the position of the player
        self.model = KNeighborsClassifier(n_neighbors = 3)
        self.model.fit(self.x, self.y)

                          
    def predict(self, coords):
        return self.model.predict([coords])
        
