import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HMGraph g = createGraph();
        printGraph( g );
        Set<String> keys = g.getKeys();
        String startCity = "Chicago";
        System.out.println();
        String layOver = getLayOverCity( keys, startCity, g);
        Vertex v = g.getThisVertex( startCity, layOver);
        try{
            System.out.println(
                    "The connections from Start: " + startCity + " with layover N: " + v.label + " are " + v.weight);
            showConnections( g, v );
        }catch (Exception e ){
            System.out.println("This city has no further connections or this is the start city");
        }

    }

    private static void printGraph(HMGraph g) {

        for (string v : g.getKeys()){
            ArrayList<Vertex> connections = g.getConnections(v);
            System.out.println("From " + v);
            for (Vertex conn : connections){
                System.out.println("City: " + conn.label + " Distance: " + conn.weight);
            }
        }
    }

    private static String getLayOverCity(Set<String> keys, String startCity, HMGraph g) {
        Scanner scan = new Scanner(System.in);

        for (String key : keys){
            System.out.println("Showing connection for key: " + key);
            ArrayList<Vertex> connections = g.getConnections(key);
            connections.forEach((connection)->
                    System.out.println("City:" + connection.label + " Dest: " + connection.weight)
            );
        }

        StringBuilder oStr = new StringBuilder();
        String cm = "";

        for (String key : keys){
            if (key.equals(startCity)){
                continue;
            }
            oStr.append(cm).append(key);
            cm = ", ";
        }
        System.out.println("Select a layover city: " + oStr);
        return scan.nextLine();
    }

    private static void showConnections(HMGraph g, Vertex layOverVertex) {
        for (String key : g.getKeys()){
            if (key.equalsIgnoreCase(layOverVertex.label)){
                ArrayList<Vertex> connections = g.getConnections(key);
                for (Vertex connection : connections){
                    System.out.println(
                                "Name: " + connection.label +
                                " Distance: " + connection.weight +
                                " Total distance: " + (connection.weight + layOverVertex.weight));
                }
            }
        }
    }

    private static HMGraph createGraph() {

        HMGraph g = new HMGraph();

        g.addVertex("Chicago");
        g.addVertex("New York");
        g.addVertex("Houston");
        g.addVertex("Orlando");
        g.addVertex("Dallas");
        g.addVertex("Atlanta");


        g.addEdge("Chicago", new Vertex("New York", 790));
        g.addEdge("Chicago", new Vertex("Atlanta", 718));
        g.addEdge("Chicago", new Vertex("Dallas", 968));

        g.addEdge("Dallas", new Vertex("Orlando", 1120));
        g.addEdge("Dallas", new Vertex("Houston", 239));

        g.addEdge("Houston", new Vertex("Orlando", 967));

        g.addEdge("Atlanta", new Vertex("New York", 870));
        g.addEdge("Atlanta", new Vertex("Dallas", 781));
        g.addEdge("Atlanta", new Vertex("Orlando", 438));

        g.addEdge("New York", new Vertex("Houston", 1647));
        g.addEdge("New York", new Vertex("Orlando", 1080));

        return g;
    }
}