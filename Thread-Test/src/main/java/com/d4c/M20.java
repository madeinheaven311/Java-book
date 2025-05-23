package com.d4c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class M20 {

    static  int n;
    static  List<Integer>[] adj;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        adj = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }


        for (int i = 0; i < n; i++) {

            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u].add(v);
            adj[v].add(u);

        }


        dist = new int[n+1];
        visited = new boolean[n+1];
        dfs(1);

        int furthestNode = findFurthestNode();
        Arrays.fill(dist , 0);
        Arrays.fill(visited , false);
        dfs(furthestNode);

        furthestNode = findFurthestNode();
        int diameter = dist[furthestNode];


        for (int i = 0; i < n; i++) {
            int longestPathWithNewLeaf = Math.max(dist[i], diameter-dist[i] +1 );
            int newDiameter = Math.max(diameter , longestPathWithNewLeaf);
            System.out.println(newDiameter);

        }
        scanner.close();

    }

    private static void dfs(int node){

        visited[node] = true;
        for( int neighbour : adj[node] ){

            if(!visited[neighbour]){

                dist[neighbour] = dist[node] + 1;
                dfs(neighbour);
            }

        }

    }


    private static int findFurthestNode(){


        int maxDist = 0;
        int furthestNode = 1;
        for (int i = 0; i < n; i++) {

            if(dist[i] > maxDist){

                maxDist  =dist[i];
                furthestNode = i;
            }

        }

        return furthestNode;
    }

}
