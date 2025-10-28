import java.util.*;
import java.io.*;

public class ArithSquare {
    final public static int X = 1000000002;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] grid = new int[3][3];
        for (int line = 0; line < 3; line++) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            grid[line][0] = inputHelper(st.nextToken());
            grid[line][1] = inputHelper(st.nextToken());
            grid[line][2] = inputHelper(st.nextToken());
        }

        grid = fillHelper(grid);
        print(grid);
        reader.close();
    }

    public static int[][] fillHelper(int[][] grid) {
        for (int x = 0; x < 3; x++) {
            if (grid[x][0] == X && grid[x][1] < X && grid[x][2] < X) {
                grid[x][0] = grid[x][1] - (grid[x][2] - grid[x][1]);
            } else if (grid[x][1] == X && grid[x][0] < X && grid[x][2] < X) {
                grid[x][1] = grid[x][0] + (grid[x][2] - grid[x][0]) / 2;
            } else if (grid[x][2] == X && grid[x][0] < X && grid[x][1] < X) {
                grid[x][2] = grid[x][1] + (grid[x][1] - grid[x][0]);
            }
        }

        for (int y = 0; y < 3; y++) {
            if (grid[0][y] == X && grid[1][y] < X && grid[2][y] < X) {
                grid[0][y] = grid[1][y] - (grid[2][y] - grid[1][y]);
            } else if (grid[1][y] == X && grid[0][y] < X && grid[2][y] < X) {
                grid[1][y] = grid[0][y] + (grid[2][y] - grid[0][y]) / 2;
            } else if (grid[2][y] == X && grid[0][y] < X && grid[1][y] < X) {
                grid[2][y] = grid[1][y] + (grid[1][y] - grid[0][y]);
            }
        }
        int count = getCount(grid);

        if (count < 9) {
            if (count == 0 || count == 1 || count == 2 || count == 3 || count == 5) {
                grid = helper2(grid, getCount(grid));
            } else {
                grid = fillHelper(grid);
            }
        }
        return grid;
    }

    public static ArrayList<Integer[]> getKnownVals(int[][] grid) {
        ArrayList<Integer[]> knownVals = new ArrayList<Integer[]>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] < X) {
                    Integer[] temp = new Integer[2];
                    temp[0] = x;
                    temp[1] = y;
                    knownVals.add(temp);
                }
            }
        }

        return knownVals;
    }

    public static int[][] helper2(int[][] grid, int count) {
        ArrayList<Integer[]> knownVals = getKnownVals(grid);

        if (count == 0) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    grid[x][y] = 1;
                }
            }
        } else if (count == 1) {
            int num = grid[knownVals.get(0)[0]][knownVals.get(0)[1]];
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    grid[x][y] = num;
                }
            }
        } else if (count == 2) {
            int x1 = knownVals.get(0)[0];
            int y1 = knownVals.get(0)[1];
            int x2 = knownVals.get(1)[0];
            int y2 = knownVals.get(1)[1];
            int a = grid[x1][y1];
            int b = grid[x2][y2];

            if (x1 == 0 && y1 == 0 && x2 == 2 && y2 == 2 && ((b - a) % 2 == 0)) {
                int val = a + (b - a) / 2;
                for (int y = 0; y < 3; y++) {
                    grid[0][y] = a;
                    grid[1][y] = val;
                    grid[2][y] = b;
                }
            } else if (x1 == 0 && y1 == 2 && x2 == 2 && y2 == 0 && ((b - a) % 2 == 0)) {
                int val = a + (b - a) / 2;
                for (int y = 0; y < 3; y++) {
                    grid[0][y] = a;
                    grid[1][y] = val;
                    grid[2][y] = b;
                }
            }

            if (x1 == 0 && y1 == 0) {
                if (x2 == 1 && y2 == 1 || x2 == 1 && y2 == 2) {
                    for (int y = 0; y < 3; y++) {
                        grid[0][y] = a;
                        grid[1][y] = b;
                        grid[2][y] = b + (b - a) ;
                    }
                } else if (x2 == 2 && y2 == 1) {
                    for (int x = 0; x < 3; x++) {
                        grid[x][0] = a;
                        grid[x][1] = b;
                        grid[x][2] = b + (b - a);
                    }
                }
            } else if (x1 == 0 && y1 == 1) {
                if (x2 == 1 && y2 == 2 || x2 == 2 && y2 == 2) {
                    for (int x = 0; x < 3; x++) {
                        grid[x][0] = a - (b - a);
                        grid[x][1] = a;
                        grid[x][2] = b;
                    }
                } else if (x2 == 1 && y2 == 0 || x2 == 2 && y2 == 0) {
                    for (int x = 0; x < 3; x++) {
                        grid[x][0] = b;
                        grid[x][1] = a;
                        grid[x][2] = a + (a - b);
                    }
                }
            } else if (x1 == 0 && y1 == 2) {
                if (x2 == 1 && y2 == 1 || x2 == 2 && y2 == 1) {
                    for (int x = 0; x < 3; x++) {
                        grid[x][0] = b - (a - b);
                        grid[x][1] = b;
                        grid[x][2] = a;
                    }
                } else if (x2 == 1 && y2 == 0) {
                    for (int y = 0; y < 3; y++) {
                        grid[0][y] = a;
                        grid[1][y] = b;
                        grid[2][y] = b + (b - a);
                    }
                }
            } else if (x1 == 1 && y1 == 0) {
                if (x2 == 2 && y2 == 1 || x2 == 2 && y2 == 2) {
                    for (int y = 0; y < 3; y++) {
                        grid[0][y] = a - (b - a);
                        grid[1][y] = a;
                        grid[2][y] = b;
                    }
                }
            } else if (x1 == 1 && y1 == 1) {
                if (x2 == 2 && y2 == 0 || x2 == 2 && y2 == 2) {
                    for (int y = 0; y < 3; y++) {
                        grid[0][y] = a - (b - a);
                        grid[1][y] = a;
                        grid[2][y] = b;
                    }
                }
            } else if (x1 == 1 && y1 == 2) {
                if (x2 == 2 && y2 == 0 || x2 == 2 && y2 == 1) {
                    for (int y = 0; y < 3; y++) {
                        grid[0][y] = a - (b - a);
                        grid[1][y] = a;
                        grid[2][y] = b;
                    }
                }
            }
        } else if (count == 3) {
            int x1 = knownVals.get(0)[0];
            int y1 = knownVals.get(0)[1];
            int x2 = knownVals.get(1)[0];
            int y2 = knownVals.get(1)[1];
            int x3 = knownVals.get(2)[0];
            int y3 = knownVals.get(2)[1];

            if (x1 == x2 && x2 == x3) {
                for (int x = 0; x < 3; x++) {
                    grid[x][0] = grid[x1][y1];
                    grid[x][1] = grid[x2][y2];
                    grid[x][2] = grid[x3][y3];
                }
            } else if (y1 == y2 && y2 == y3) {
                for (int y = 0; y < 3; y++) {
                    grid[0][y] = grid[x1][y1];
                    grid[1][y] = grid[x2][y2];
                    grid[2][y] = grid[x3][y3];
                }
            } else if (x1 == 0 && y1 == 0 && x2 == 1 && y2 == 2 && x3 == 2 && y3 == 1) {
                grid[1][1] = grid[x3][y3];
                grid[0][1] = grid[x3][y3];
                grid[0][2] = grid[0][1] + (grid[0][1] - grid[0][0]);
                grid[2][2] = grid[1][2] + (grid[1][2] - grid[0][2]);
                grid[1][0] = grid[1][1] - (grid[1][2] - grid[1][1]);
                grid[2][0] = grid[2][1] - (grid[2][2] - grid[2][1]);
            } else if (x1 == 0 && y1 == 2 && x2 == 1 && y2 == 0 && x3 == 2 && y3 == 1) {
                grid[1][1] = grid[x3][y3];
                grid[0][1] = grid[x3][y3];
                grid[1][2] = grid[1][1] + (grid[1][1] - grid[1][0]);
                grid[0][0] = grid[0][1] - (grid[0][2] - grid[0][1]);
                grid[2][0] = grid[1][0] + (grid[1][0] - grid[0][0]);
                grid[2][2] = grid[1][2] + (grid[1][2] - grid[0][2]);
            } else if (x1 == 0 && y1 == 1 && x2 == 1 && y2 == 0 && x3 == 2 && y3 == 2) {
                grid[1][1] = grid[x1][y1];
                grid[2][1] = grid[x1][y1];
                grid[2][0] = grid[2][1] - (grid[2][2] - grid[2][1]);
                grid[0][0] = grid[1][0] - (grid[2][0] - grid[1][0]);
                grid[0][2] = grid[0][1] + (grid[0][1] - grid[0][0]);
                grid[1][2] = grid[1][1] + (grid[1][1] - grid[1][0]);
            } else if (x1 == 0 && y1 == 1 && x2 == 1 && y2 == 2 && x3 == 2 && y3 == 0) {
                grid[1][1] = grid[x1][y1];
                grid[2][1] = grid[x1][y1];
                grid[2][2] = grid[2][1] + (grid[2][1] - grid[2][0]);
                grid[0][2] = grid[1][2] - (grid[2][2] - grid[1][2]);
                grid[0][0] = grid[0][1] - (grid[0][2] - grid[0][1]);
                grid[1][0] = grid[1][1] - (grid[1][2] - grid[1][1]);
            } else if (x1 == 0 && y1 == 0 && x2 == 1 && y2 == 1 && x3 == 2 && y3 == 2) {
                grid[0][1] = grid[x2][y2];
                grid[2][1] = grid[x2][y2];
                grid[2][0] = grid[2][1] - (grid[2][2] - grid[2][1]);
                grid[0][2] = grid[0][1] + (grid[0][1] - grid[0][0]);
                grid[1][2] = grid[0][2] + (grid[2][2] - grid[0][2]) / 2;
                grid[1][0] = grid[0][0] + (grid[2][0] - grid[0][0]) / 2;
            } else if (x1 == 0 && y1 == 2 && x2 == 1 && y2 == 1 && x3 == 2 && y3 == 0) {
                grid[0][1] = grid[x2][y2];
                grid[2][1] = grid[x2][y2];
                grid[0][0] = grid[0][1] - (grid[0][2] - grid[0][1]);
                grid[2][2] = grid[2][1] + (grid[2][1] - grid[2][0]);
                grid[1][0] = grid[0][0] + (grid[2][0] - grid[0][0]) / 2;
                grid[1][2] = grid[0][2] + (grid[2][2] - grid[0][2]) / 2;
            }
        } else if (count == 5) {
            int row = -1;
            int col = -1;
            for (int x = 0; x < 3; x++) {
                int c = 0;
                row = x;
                for (int y  = 0; y < 3; y++) {
                    if (grid[x][y] < X) {
                        c++;
                    }
                }

                if (c == 3) {
                    break;
                }
            }

            for (int y = 0; y < 3; y++) {
                int c = 0;
                col = y;
                for (int x  = 0; x < 3; x++) {
                    if (grid[x][y] < X) {
                        c++;
                    }
                }

                if (c == 3) {
                    break;
                }
            }

            grid = helper5(grid, row, col);
        }
        
        return grid;
    }

    public static int[][] helper5(int[][] grid, int row, int col) {
        int d = grid[2][col] - grid[1][col];

        if (row == 0) {
            grid[1][0] = grid[0][0] + d;
            grid[1][1] = grid[0][1] + d;
            grid[1][2] = grid[0][2] + d;
            grid[2][0] = grid[0][0] + 2 * d;
            grid[2][1] = grid[0][1] + 2 * d;
            grid[2][2] = grid[0][2] + 2 * d;
        } else if (row == 1) {
            grid[0][0] = grid[1][0] - d;
            grid[0][1] = grid[1][1] - d;
            grid[0][2] = grid[1][2] - d;
            grid[2][0] = grid[1][0] + d;
            grid[2][1] = grid[1][1] + d;
            grid[2][2] = grid[1][2] + d;
        } else if (row == 2) {
            grid[0][0] = grid[2][0] - 2 * d;
            grid[0][1] = grid[2][1] - 2 * d;
            grid[0][2] = grid[2][2] - 2 * d;
            grid[1][0] = grid[2][0] - d;
            grid[1][1] = grid[2][1] - d;
            grid[1][2] = grid[2][2] - d;
        }

        return grid;
    }

    public static int getCount(int[][] grid) {
        int count = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] < X) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void print(int[][] grid) {
        for (int x = 0; x < 3; x++) {
            String str = "";
            for (int y = 0; y < 3; y++) {
                str += grid[x][y] + " ";
            }

            str = str.substring(0, str.length() - 1);
            System.out.println(str);
        }
    }

    public static int inputHelper(String str) {
        if (str.equals("X")) {
            return X;
        } 
        return Integer.parseInt(str);
    }
}
