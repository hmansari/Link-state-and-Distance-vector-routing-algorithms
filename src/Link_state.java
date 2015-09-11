//package ip_p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Link_state {
    
   // public static ArrayList<topology> nodes=new ArrayList<topology>();
    public static float matrix[][];
    public static float distance[], metric;
   // public static Queue<Integer> que=new LinkedList<Integer>();
    public static boolean[] visited;
    public static int preD[];
    public static int count_vertices, vert1, vert2;
    public static int nextnode = 0;
    public static int nexthop[];
//    public static List<Float> list=new ArrayList<Float>();
	
   // public static int infinity=Integer.MAX_VALUE;
    
   public static void dijkstra(int src, int dest){
       int next;
       int count=0;
       
        visited = new boolean[count_vertices+1];
        distance = new float[count_vertices+1];
        preD = new int[count_vertices+1];
        nexthop = new int[count_vertices+1];
      
        //initializing vertices to be false
       for (int i=1; i<=count_vertices; i++){
           distance[i]=Float.MAX_VALUE;
           visited[i]=false;
           preD[1]=0;
           
       }
       //assigning next as source whose distance = 0 and visited node
       next=src;
       distance[next]=0;
       visited[next]=true;
       
       do
 		{
                      float max=Float.MAX_VALUE;
 			for(int i=1;i<=count_vertices;i++)
 			{
 				if(max>distance[i] && visited[i]!=true)
                                                {
 					
 							max=distance[i];
 							next=i;
 						}	
 					
 			}
                        visited[next]=true;
 			for(int i=1;i<=count_vertices;i++)
 			{
 					if(matrix[next][i]!=0 && visited[i]!=true)
 					{
 						if(matrix[next][i]+distance[next]<distance[i])
 						{
 							distance[i]=matrix[next][i]+distance[next];
 							preD[i]=next;	
 						}	
 					} 		
			}	
 			
 			count++;
                }while(visited[dest]!=true || count<=count_vertices);
                
       routing_table(src);
 
   }
   
   public static void routing_table(int s){
       
                System.out.println("\nRouting table of Node "+s+": ");
 		System.out.println("Destination"+ "   " + "|" + "   " + "Nexthop");
 		
 		
 		nexthop[s]=Integer.MAX_VALUE;
 		for(int t=1;t<=count_vertices;t++)
		{int q=t;
 			while(q!=s)
	 		{
	 			if(preD[q]==s){
                                    nexthop[t]=q;
                                    break;}
	 			q=preD[q];
	 		}
		}
 		
 		for(int l=1;l<=count_vertices;l++)
 		{
 			if(l!=s)
 			{
                            System.out.println(l+"\t\t"+nexthop[l]);
                        }
 		}
      
   }

    public static void main (String args[]) throws Exception {
        
        String input = null;
           
        System.out.println("Enter the command in this format:");
        System.out.println("link_state file-name node1 node2");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        String[] Split_Input = input.split(" ");
        
        System.out.println("Invoking "+Split_Input[0]);
        
        File file=new File(Split_Input[1]+".txt");
         if (!file.exists()) 
	    {
		System.out.println("File Does Not Exist");
            }
        FileReader file_name=new FileReader(file);
        BufferedReader br_file=new BufferedReader(file_name);
        int node_1 = Integer.parseInt(Split_Input[2]);
        int node_2 = Integer.parseInt(Split_Input[3]);
        
        count_vertices = Integer.parseInt(br_file.readLine());
        System.out.println("Number of nodes: "+count_vertices);
        
        matrix = new float[count_vertices+1][count_vertices+1];
        
        //initializing the weights and the rest with infinity 
        for(int i=0;i<count_vertices;i++){
            for (int j=0;j<count_vertices;j++){
                if (i==j){
                    matrix[i][j]=0;
                }
                else
                    matrix[i][j]=Float.MAX_VALUE;
            }
            
        }
        
        String read_file;
        String[] eachValue;
        while((read_file=br_file.readLine())!=null)
        {
            eachValue=read_file.split(" ");
            vert1 = Integer.parseInt(eachValue[0]);
            vert2 = Integer.parseInt(eachValue[1]);
            metric = Float.parseFloat(eachValue[2]);
            matrix[vert1][vert2]=metric;
            matrix[vert2][vert1]=metric;
        }
       
      //  System.out.println(2*list.size());
//        for(int i=0;i<count_vertices;i++){
//            for (int j=0;j<count_vertices;j++){
//                
//              System.out.println("matrix is"+ matrix[i][j]);
//            }
//        }
     dijkstra(node_1,node_2);
     System.out.println(" The cost between "+ node_1 +" and "+ node_2 +" is "+distance[node_2]); 
     
     dijkstra(node_2,node_1);
  
    }
}
		

			
    
