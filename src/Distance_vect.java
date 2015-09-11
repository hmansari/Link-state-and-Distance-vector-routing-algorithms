//package javaapplication11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Map {
        int source;
	int destination;
	float metric;
        
        public Map(){
            
        }
}


public class Distance_vect {
	

	public static Queue<Integer> que=new LinkedList<Integer>();
        public static List<Map> list=new ArrayList<Map>();
	public static int infinity=Integer.MAX_VALUE;
       // public static Queue que = new Queue();
	//public static int count=0;
	public static int converge[];
	public static int count_vertices;
      //  public static boolean[][] visited;
        public static float matrix[][];
        public static int nexthop[][];
        public static int size_list;

	public static void DistanceVector(int s,int from_node,int to_node)
	{
                Boolean flag=false;	
              //  visited = new boolean[count_vertices][count_vertices];
		for(int f=0;f<list.size();f++)
		{			
		if(!que.contains(list.get(f).destination)&&list.get(f).source==s)
		//if(list.get(f).Edge_source==s)
		que.add(list.get(f).destination);	               
		}
                //System.out.println(que);
	
	converge=new int[count_vertices];	
	
	for(int l=0;l<count_vertices;l++)
		converge[l]=1;
               
	
	while(!que.isEmpty())
	{

		flag=false;
		int i=que.remove();
		
		for(int j=0;j<count_vertices;j++)
		{
				for(int k=0;k<count_vertices;k++)
				{
						if(matrix[i][j]>matrix[i][k]+matrix[k][j])
						{
							matrix[i][j]=matrix[i][k]+matrix[k][j];
							flag=true;
							
							for(int p=0;p<count_vertices;p++)
							{
								if(list.get(p).source==i && list.get(p).destination==k)
								nexthop[i][j]=k;
								else
								nexthop[i][j]=nexthop[i][k];
								break;
							}
							for(int f=0;f<list.size();f++)
							{
								if(list.get(f).source==i)
								{
									if(!que.contains(list.get(f).destination))
									que.add(list.get(f).destination);	
                                                                        
									
								}
							}
							
						}
				}
			
		}
		
		if(flag==true)
			converge[i]++;
	}
	
	
	
        System.out.println("No.of iterations:");
	
	int iter=0;
	for(int p=0;p<count_vertices;p++)
	{
	if(iter<converge[p])
	{
		iter=converge[p];
                break;
		
	}
	}
	
	
	System.out.println("Maximum number of iterations to converge as initial node "+(s+1)+" is "+ iter+"\n");

	System.out.println("The least cost path between "+ (from_node+1) +" and "+ (to_node+1) +" : " + matrix[from_node][to_node]);
        
	System.out.println("\nRouting table of Node "+ (from_node+1) +": ");

	System.out.println("Destination"+ "   " + "|" + "   " + "Nexthop");

	for(int j=0;j<count_vertices;j++)
	{
            if (j!=from_node)
            {
		System.out.println((j+1) + "\t\t" + (nexthop[from_node][j]+1));	
            }
	}	

	System.out.println("\nRouting table of Node "+ (to_node+1) +": ");
	
	System.out.println("Destination"+ "   " + "|" + "   " + "Nexthop");
	
	for(int j=0;j<count_vertices;j++)
	{
             if (j!=to_node)
            {
		System.out.println((j+1) + "\t\t" + (nexthop[to_node][j]+1));	
            }
	}	
		
	
}

	public static void main(String[] args) throws IOException 
	
	{     
            String input = null;
            
            
        System.out.println("Enter the command in this format:");
        System.out.println("distance_vector initial_node file-name node1 node2");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        String[] Split_Input = input.split(" ");
		
        System.out.println("Invoking "+Split_Input[0]);
        
        File file=new File(Split_Input[2]+".txt");
         if (!file.exists()) 
	    {
		System.out.println("File Does Not Exist");
            }
        FileReader file_name=new FileReader(file);
        BufferedReader br_file=new BufferedReader(file_name);
        int s_node = Integer.parseInt(Split_Input[3]);
        int d_node = Integer.parseInt(Split_Input[4]);
        int initial_node=Integer.parseInt(Split_Input[1]);
        
        count_vertices = Integer.parseInt(br_file.readLine());
            
          System.out.println("Number of nodes: "+count_vertices);
        
        matrix = new float[count_vertices][count_vertices];
        nexthop = new int[count_vertices][count_vertices];
        
        
        String read_file;
        String[] eachValue;
        while((read_file=br_file.readLine())!=null)
        {
            eachValue=read_file.split(" ");
            int i = Integer.parseInt(eachValue[0]);
            int j = Integer.parseInt(eachValue[1]);
            float value = Float.parseFloat(eachValue[2]);
            Map graph = new Map();
            graph.source = i-1;
            graph.destination = j-1;
            graph.metric = value;
            list.add(graph);
            
            Map graph_rev = new Map();
            graph_rev.source = j-1;
            graph_rev.destination = i-1;
            graph_rev.metric = value;
            list.add(graph_rev);  
//            matrix[i][j]=value;
//            matrix[j][i]=value;
        }

        
          for(int i=0;i<count_vertices;i++){
            for (int j=0;j<count_vertices;j++){
                if (i==j){
                    matrix[i][j]=0;
                    nexthop[i][j]=j;
                }
                else
                {  
                    int z=0;
                    for (int l=0;l<list.size();l++){
                        if (list.get(l).source==i && list.get(l).destination==j){
                            matrix[i][j]=list.get(l).metric;
                            nexthop[i][j]=j;
                            z++;
                        }
                        
                    }
                    if (z==0){
                    matrix[i][j]=Float.MAX_VALUE;
                    nexthop[i][j]=j;
                    
                      }
                    
                }
          
            
            }
         
          }
		DistanceVector(initial_node-1,s_node-1,d_node-1);
		
	}
 
}

