package jeudelavie.librairies;

import java.util.*;
import java.io.*;


class Mer {

  private final static int SEA_SIZE = 5;
  private final static Object EMPTY = null;

  private int width;
  private int height;
  private int numSardines;
  private int numRequins;
  private Object[][] sea;

  public Mer (int width, int height, int numSardines, int numRequins)
  {
    this.width = width;
    this.height = height;
    this.sardines = new Sardine[numSardines];
    this.requins = new Requin[numRequins];
    this.sea = new Object[width][height];
  }

  public Mer (int numSardines, int numRequins)
  {
    this(SEA_SIZE,SEA_SIZE,numSardines,numRequins);
  }

  public void initializeSea()
  {
  	for (int i = 0; i < this.width; i++)
    {
  		for (int j = 0; j < this.height; j++)
      {
  			this.sea[i][j]=EMPTY;
  		}
  	}
  }

  public void creerSardines ()
  {
    for (int i = 0; i < this.numSardines.lenght; i++)
    {
      this.sardines[i] = new Sardine();
    }
  }

  public void creerRequins ()
  {
    for (int i = 0; i < this.numRequins.lenght; i++)
    {
      this.requins[i] = new Sardine();
    }
  }

  public void displaySea()
  {
   for (int i = 0; i < this.width; i++)
   {
    for (int j = 0; j < this.height; j++)
    {
     if (this.sea[i][j] == EMPTY)
      System.out.print("[X]");
     else
     System.out.print(this.sea[i][j] + "\t");
    }
    System.out.print("\n");
  }
}

}
