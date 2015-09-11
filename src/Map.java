
//package dv.udp;

public class Map {
    String ip_address;
        int port_no;
        int node;
        int source;
	int destination;
	float metric;
        public Map(String ip, int prt, int nd, int s, int d, float cost){
            this.ip_address = ip;
            this.port_no = prt;
            this.node = nd;
            this.source = s;
            this.destination = d;
            this.metric = cost;
            
        }
    
}
