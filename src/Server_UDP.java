
//package dv.udp;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;




public class Server_UDP  {

   
    public static int mss_value,port_value,s_node,d_node,initial_node,count_vertices;
    public static List<Map> list=new ArrayList<Map>();
    public static Queue<Integer> que=new LinkedList<Integer>();
    public static int converge[];
    public static String file_path;
    public static float matrix[][];
    public static int nexthop[][];
    public static String ip_add;  
    DatagramPacket sendPacket=null;
    public static DatagramSocket server;

           
    public static void main(String[] args) throws Exception {
        
           String input = null;
           boolean flag = true;
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           System.out.println("Invoking Server");
                         
           System.out.println("Enter IP address:  ");
           ip_add=br.readLine();
                   
           System.out.println("Enter the file name");
           file_path = br.readLine()+".txt";
                        
           System.out.println("Enter the port number");
           port_value=Integer.parseInt(br.readLine());
           
           System.out.println("Enter node number");
           initial_node=Integer.parseInt(br.readLine());
           
           File file=new File(file_path);
           FileReader file_name=new FileReader(file);
            BufferedReader br_file=new BufferedReader(file_name);
            
            count_vertices = Integer.parseInt(br_file.readLine());
            System.out.println("Number of nodes: "+count_vertices);
            
            matrix = new float[count_vertices][count_vertices];
            String read_file;
            String[] eachValue;
        while((read_file=br_file.readLine())!=null)
        {
            eachValue=read_file.split(" ");
            int i = Integer.parseInt(eachValue[0]);
            int j = Integer.parseInt(eachValue[1]);
            float value = Float.parseFloat(eachValue[2]);
            Map graph = new Map(ip_add, 7735, initial_node, (i-1), (j-1), value);
            list.add(graph);

        }

          for(int i=0;i<count_vertices;i++){
            for (int j=0;j<count_vertices;j++){
                if (i==j){
                    matrix[i][j]=0;
                }
                else
                {  
                    int z=0;
                    for (int l=0;l<list.size();l++){
                        if (list.get(l).source==i && list.get(l).destination==j){
                            matrix[i][j]=list.get(l).metric;
                            z++;
                        }
                        
                    }
                    if (z==0){
                    matrix[i][j]=Float.MAX_VALUE;
                    
                      }
                    
                }
          
            
            }
         
          }
         
          server=new DatagramSocket(port_value);
           server.setSoTimeout(50);
  
           byte[] sendDistanceVector = new byte[count_vertices];
		//sendDistanceVector[0] = (byte)0.0;
		for (int i=0; i<count_vertices;i++){
			sendDistanceVector[i]=(byte)matrix[initial_node][i];
			
		}
                 float sum1 = 0;
                 for (int i=0; i<count_vertices;i++){
			sum1+=matrix[initial_node][i];
			
		}
   
           for (int i=0;i<list.size();i++){
				//DatagramSocket dsoc = new DatagramSocket(1000);
			DatagramPacket sendPacket= new DatagramPacket(sendDistanceVector, 0,
			sendDistanceVector.length, InetAddress.getByName(list.get(i).ip_address),list.get(i).port_no);
			server.send(sendPacket);
		}
         
           
               byte[] buf = new byte[count_vertices];
		    DatagramPacket receive_packet = new DatagramPacket(buf, buf.length);
                    server.receive(receive_packet);
                    
                    InetAddress IP=receive_packet.getAddress();
			String IPAddr = IP.toString();
			int port = receive_packet.getPort();
                        int node_received = 0;
                        for (int i=0;i<list.size();i++){
				if(IPAddr.contains(list.get(i).ip_address)){
					if(port == list.get(i).port_no){
						node_received=list.get(i).node;
					}
				}
			}
                        
                        float[] distance_vctor = new float[count_vertices];
			byte[] dv_rec=receive_packet.getData();
             for(int i=0;i<count_vertices;i++){
            	 distance_vctor[i] = dv_rec[i];
             }
                        
             for (int i=0;i<count_vertices;i++){
            	 matrix[node_received][i]=distance_vctor[i];
             }
             
             boolean flag2 = false;
             
            float sum=0;
            for (int i=0; i<count_vertices;i++){
			sum=matrix[initial_node][node_received]+matrix[node_received][i];
                        
                        if (sum <= matrix[initial_node][i]){
                            matrix[node_received][i]=sum;
                            flag2=true;
                        }
			
		}
           
             System.out.println("The adjacency matrix of "+ initial_node + " is: \n");
             for (int i=0;i<count_vertices;i++){
            	 System.out.println(matrix[node_received][i]+1);
             
            }
                   
             }
}


  
        
   

   
    
          
            
            
            
            
                         
      
          
        
             

              
                            