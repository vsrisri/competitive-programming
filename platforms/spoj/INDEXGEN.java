import java.util.*;
import java.io.*;

public class INDEXGEN {
    public static Map<String, String> priDis = new LinkedHashMap<>();
    public static Map<String, Map<String, String>> secDis = new LinkedHashMap<>();
    public static Map<String, TreeMap<String, List<Integer>>> index = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int docNum = 1;
        String line;

        while ((line = br.readLine()) != null) {
            if (line.equals("**")) {
                break;
            }
            if (line.equals("*")) {
                helper(sb.toString(), docNum);
                docNum++;
                sb.setLength(0);
            } else {
                sb.append(line).append("\n");
            }
        }
    }

    public static void helper(String text, int docNum) {
        priDis = new LinkedHashMap<>();
        secDis = new LinkedHashMap<>();
        index = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        int page = 1;
        int i = 0;
        char[] chars = text.toCharArray();
        while (i < chars.length) {
            if (chars[i] == '&') {
                page++;
                i++;
            } else if (chars[i] == '{') {
                StringBuilder markerSb = new StringBuilder();
                i++;
                while (i < chars.length && chars[i] != '}') {
                    if (chars[i] == '\n') {
                        markerSb.append(' ');
                    } else {
                        markerSb.append(chars[i]);
                    }
                    i++;
                }
                i++;
                marker(markerSb.toString(), page);
            } else {
                i++;
            }
        }

        System.out.println("DOCUMENT " + docNum);
        pIdx();
    }

    public static void marker(String marker, int page) {
        String text = "";
        String pri = null;
        String sec = null;
        int perIdx = -1;
        int dolIdx = -1;
        for (int i = 0; i < marker.length(); i++) {
            if (marker.charAt(i) == '%' && perIdx == -1) {
                perIdx = i;
            } else if (marker.charAt(i) == '$' && dolIdx == -1) {
                dolIdx = i;
            }
        }

        if (perIdx != -1 && dolIdx != -1) {
            text = trim(marker.substring(0, perIdx));
            pri = trim(marker.substring(perIdx + 1, dolIdx));
            sec = trim(marker.substring(dolIdx + 1));
        } else if (perIdx != -1) {
            text = trim(marker.substring(0, perIdx));
            pri = trim(marker.substring(perIdx + 1));
        } else if (dolIdx != -1) {
            text = trim(marker.substring(0, dolIdx));
            sec = trim(marker.substring(dolIdx + 1));
        } else {
            text = trim(marker);
        }

        String priKey = (pri != null) ? pri : text;
        if (!index.containsKey(priKey)) {
            index.put(priKey, new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        }

        if (!priDis.containsKey(priKey.toLowerCase())) {
            priDis.put(priKey.toLowerCase(), priKey);
        }

        if (sec != null) {
            Map<String, List<Integer>> secMap = index.get(priKey);
            if (!secMap.containsKey(sec)) {
                secMap.put(sec, new ArrayList<>());
            }
            List<Integer> pages = secMap.get(sec);
            if (!pages.contains(page)) {
                pages.add(page);
            }

            String pk = priKey.toLowerCase();
            if (!secDis.containsKey(pk)) {
                secDis.put(pk, new LinkedHashMap<>());
            }
            if (!secDis.get(pk).containsKey(sec.toLowerCase())) {
                secDis.get(pk).put(sec.toLowerCase(), sec);
            }
        } else {
            Map<String, List<Integer>> secMap = index.get(priKey);
            if (!secMap.containsKey("")) {
                secMap.put("", new ArrayList<>());
            }
            List<Integer> pages = secMap.get("");
            if (!pages.contains(page)) {
                pages.add(page);
            }
        }
    }

    public static String trim(String s) {
        s = s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        return s;
    }

    public static void pIdx() {
        List<String> priKeys = new ArrayList<>(index.keySet());
        priKeys.sort(String.CASE_INSENSITIVE_ORDER);
        for (String pk : priKeys) {
            String dis = priDis.getOrDefault(pk.toLowerCase(), pk);
            Map<String, List<Integer>> secMap = index.get(pk);
            List<Integer> priPages = secMap.getOrDefault("", new ArrayList<>());
            Collections.sort(priPages);
            StringBuilder line = new StringBuilder(dis);
            if (!priPages.isEmpty()) {
                for (int p : priPages) {
                    line.append(", ").append(p);
                }
            }
            System.out.println(line.toString());
            List<String> secKeys = new ArrayList<>(secMap.keySet());
            secKeys.remove("");
            secKeys.sort(String.CASE_INSENSITIVE_ORDER);
            for (String sk : secKeys) {
                String skDis = sk;
                if (secDis.containsKey(pk.toLowerCase()) && secDis.get(pk.toLowerCase()).containsKey(sk.toLowerCase())) {
                    skDis = secDis.get(pk.toLowerCase()).get(sk.toLowerCase());
                }
                List<Integer> pages = secMap.get(sk);
                Collections.sort(pages);
                StringBuilder secLine = new StringBuilder("+ " + skDis);
                for (int p : pages) {
                    secLine.append(", ").append(p);
                }
                System.out.println(secLine.toString());
            }
        }
    }
}
