package secondproject;

import java.util.Map;
import java.util.TreeMap;

public class SecondProject {

    public static void main(String[] args) {

        Map<String, Integer> map = new TreeMap<>();
        Map<String, Double> map2 = new TreeMap<>();
        Map<String, Double> map3 = new TreeMap<>();
        Map<String, String> map4 = new TreeMap<>();
        Map<String, String> map5 = new TreeMap<>();
        Map<String, String> map6 = new TreeMap<>();

        String dados[] = new String[6];
             
                
        
        // 1 - NUMERO DE REVIEWS DE CADA PLATAFORMA
        SimpleReader reader = new SimpleReader("game-reviews.csv");
        String line = reader.readLine();
        
        while (line != null) {
            if (line.equals("title;platform;score_phrase;score;genre;editors_choice;release_year")) {
            } else {
                dados = line.split(";");
                String plataforma = String.valueOf(dados[1]);
                if (!map.containsKey(plataforma)) {
                    map.put(plataforma, 1);
                } else {
                    int numAtual = map.get(plataforma);
                    int numAtualizado = numAtual + 1;
                    map.replace(plataforma, numAtualizado);
                }
            }
            line = reader.readLine();
        }
        reader.close();

        System.out.println();
        System.out.println("---NUMERO DE REVIEWS DE CADA PLATAFORMA:");
        System.out.println();

        for (String c : map.keySet()) {
            System.out.println(c + ": " + map.get(c));
        }

        
        
        // 2 - PERCENTUAL DE GREAT REVIEWS (SOBRE O NUMERO DE REVIEWS DA PLATAFORMA)
        SimpleReader reader2 = new SimpleReader("game-reviews.csv");
        String line2 = reader2.readLine();

        while (line2 != null) {
            if (line2.equals("title;platform;score_phrase;score;genre;editors_choice;release_year")) {
            } else {
                dados = line2.split(";");
                String plataforma = String.valueOf(dados[1]);
                if (!map2.containsKey(plataforma) && dados[2].equals("Great")) {
                    map2.put(plataforma, 1.00);
                } else {
                    if (map2.containsKey(plataforma) && dados[2].equals("Great")) {
                        double numAtual = map2.get(plataforma);
                        double numAtualizado = numAtual + 1;
                        map2.replace(plataforma, numAtualizado);
                    }
                }
            }
            line2 = reader2.readLine();
        }

        reader2.close();

        for (String c : map.keySet()) {
            if (map.containsKey(c) && map2.containsKey(c)) {
                int totalReviews = map.get(c);
                double totalGreats = map2.get(c);
                double percentualGreats = 100.00000 / totalReviews * totalGreats;
                map2.replace(c, percentualGreats);
            }
        }

        System.out.println();
        System.out.println("---PERCENTUAL DE GREAT REVIEWS:");
        System.out.println();
        for (String c : map2.keySet()) {
            System.out.println(c + ": " + map2.get(c) + "%");
        }

        
        
        // 3 - MÉDIA ARITMÉTICA DOS SCORES
        SimpleReader reader3 = new SimpleReader("game-reviews.csv");
        String line3 = reader3.readLine();

        while (line3 != null) {
            if (line3.equals("title;platform;score_phrase;score;genre;editors_choice;release_year")) {
            } else {
                dados = line3.split(";");
                String plataforma = String.valueOf(dados[1]);
                Double score = Double.parseDouble(dados[3]);
                if (!map3.containsKey(plataforma)) {
                    map3.put(plataforma, score);
                } else {
                    Double scoreAtual = map3.get(plataforma);
                    Double scoreAtualizado = scoreAtual + score;
                    map3.replace(plataforma, scoreAtualizado);
                }
            }
            line3 = reader3.readLine();
        }
        reader3.close();

        for (String c : map3.keySet()) {
            int totalReviews = map.get(c);
            System.out.println(c + " TOTAL REVIEWS: " + totalReviews);
            double score = map3.get(c);
            System.out.println(c + " score " + score);
            double mediaScore = score / totalReviews;
            map3.replace(c, mediaScore);
        }

        System.out.println();
        System.out.println("---MÉDIA ARITMETICA DOS SCORES:");
        System.out.println();
        for (String c : map3.keySet()) {
            System.out.println(c + ": " + map3.get(c));
        }

        
        
        // 4 - DESVIO PADRÃO POPULACIONAL DOS SCORES
        /*while (line != null) {
            dados = line.split(";");
            String plataforma = String.valueOf(dados[1]);
            int i = 1;
            if (!map.containsKey(plataforma)) {
                map.put(plataforma, i);
            } else {
                int numAtual = map.get(plataforma);
                int numAtualizado = numAtual + 1;
                map.replace(plataforma, numAtualizado);
            }
            line = reader.readLine();
        }
        reader.close();

        for (String c : map.keySet()) {
            //System.out.println(c + ": " + map.get(c));
        }*/
        
        
        
        // 5 - MELHOR JOGO (BASTA INDICAR UM ENTRE OS DE MAIOR SCORE)
        
        SimpleReader reader5 = new SimpleReader("game-reviews.csv");
        String line5 = reader5.readLine();

        Map<String, Double> map7 = new TreeMap<>();
        while (line5 != null) {
            if (line5.equals("title;platform;score_phrase;score;genre;editors_choice;release_year")) {
            } else {
                dados = line5.split(";");
                String game = dados[0];
                String plataforma = dados[1];
                double score = Double.parseDouble(dados[3]);
                if (!map5.containsKey(plataforma)) {
                    map5.put(plataforma, game);
                    map7.put(plataforma, score);
                    
                } else {
                    double ultimoScore = map7.get(plataforma);
                    if (score > ultimoScore) {
                        map5.replace(plataforma, game);
                        map7.replace(plataforma, score);
                    }
                }
            }
            line5 = reader5.readLine();
        }
        reader5.close();
        
        System.out.println();
        System.out.println("---MELHOR JOGO:");
        System.out.println();

        for (String c : map5.keySet()) {
            System.out.println(c + ": " + map5.get(c));
        }
        
        

        // 6 - PIOR JOGO (BASTA INDICAR UM ENTRE OS DE MENOR SCORE)
        SimpleReader reader6 = new SimpleReader("game-reviews.csv");
        String line6 = reader6.readLine();

        Map<String, Double> map8 = new TreeMap<>();
        while (line6 != null) {
            if (line6.equals("title;platform;score_phrase;score;genre;editors_choice;release_year")) {
            } else {
                dados = line6.split(";");
                String game = dados[0];
                String plataforma = dados[1];
                double score = Double.parseDouble(dados[3]);
                if (!map6.containsKey(plataforma)) {
                    map6.put(plataforma, game);
                    map8.put(plataforma, score);
                } else {
                    double ultimoScore = map8.get(plataforma);
                    if (score < ultimoScore) {
                        map6.replace(plataforma, game);
                        map8.replace(plataforma, score);
                    }
                }
            }
            line6 = reader6.readLine();
        }
        reader6.close();

        System.out.println();
        System.out.println("---PIOR JOGO:");
        System.out.println();

        for (String c : map6.keySet()) {
            System.out.println(c + ": " + map6.get(c));
        }
    }

}
