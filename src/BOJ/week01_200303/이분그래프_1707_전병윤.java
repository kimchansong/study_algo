import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class boj01707 {

    static int TestCase = 0;
    static int V;
    static int E;
    static Map<Integer, String> Map;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        TestCase = sc.nextInt();
        for(int t = 0; t < TestCase; t++){
            V = sc.nextInt();
            E = sc.nextInt();
            Map = new HashMap<Integer, String>();

            boolean isYes = true;
            for(int i = 0 ; i < E; i++){
                int s = sc.nextInt();
                int e = sc.nextInt();

                if(Map.containsKey(s) == false && Map.containsKey(e) == false){
                    Map.put(s, "RED");
                    Map.put(e, "BLACK");
                } else if (Map.get(s) == "RED" && Map.containsKey(e) == false) {
                    Map.put(e, "BLACK");
                } else if (Map.get(s) == "BLACK" && Map.containsKey(e) == false){
                    Map.put(e, "RED");
                } else if (Map.get(e) == "RED" && Map.containsKey(s) == false) {
                    Map.put(s, "BLACK");
                } else if (Map.get(e) == "BLACK" && Map.containsKey(s) == false){
                    Map.put(s, "RED");
                } else if (Map.get(s) == Map.get(e)) {
                    System.out.println("NO");
                    isYes = false;
                    break;
                }
            }

            if(isYes)
                System.out.println("YES");
        }
    }
}
