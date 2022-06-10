import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.reflect.Field;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

class Movie {
    private final String releaseDate;
    private final int month;
    private final int day;
    private final int year;
    private final String title;
    private final String overview;
    private final String popularity;
    private final String count;
    private final String average;
    private final String language;
    private final String genre;
    private final String posterUrl;

    public Movie(String date, String t, String over, String popular, String cnt, String avg, String lang, String gen, String url) {
        releaseDate = date;
        title = t;
        overview = over;
        popularity = popular;
        count = cnt;
        average = avg;
        language = lang;
        genre = gen;
        posterUrl = url;
        String[] array = releaseDate.split("/");
        this.month = parseInt(array[0]);
        this.day = parseInt(array[1]);
        this.year = parseInt(array[2]);
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getTitle() {
        return title;
    }
    public String getOverview() {
        return overview;
    }
    public String getPopularity() {
        return popularity;
    }
    public String getCount() {
        return count;
    }
    public String getAverage() {
        return average;
    }
    public String getLanguage() {
        return language;
    }
    public String getGenre() {
        return genre;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String toString() {
        return "releaseDate: "+this.releaseDate+", title: "+this.title+", overview: "+this.overview+", popularity: "+this.popularity+", count: "+this.count+", average: "+this.average+", language: "+this.language+", genre: "+this.genre+", posterUrl: "+this.posterUrl;
    }
}

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws SecurityException, IndexOutOfBoundsException {
        ArrayList<Movie> movielist = new ArrayList<>();
        ArrayList<Movie> updatedlist;
        File reader = new File("mymoviedb.csv");
        try(FileReader reader1 = new FileReader(reader); BufferedReader reader2 = new BufferedReader(reader1)) {
            String line;
            String[] regex;
            int i = 0;
            while((line = reader2.readLine()) != null) {
                i++;
                if(i == 1)
                    continue;
                regex = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                movielist.add(new Movie(regex[0], regex[1], regex[2], regex[3], regex[4], regex[5], regex[6], regex[7], regex[8]));
            }
        } catch(Exception exception) {
            System.out.println("Error!");
        }
        System.out.println("\n*---------------------------------------*");
        System.out.println("|    1. to list all the entities        |");
        System.out.println("|    2. to sort the entities            |");
        System.out.println("|    3. to search the entities          |");
        System.out.println("|    4. to list column names            |");
        System.out.println("|    5. to filter entities              |");
        System.out.println("|    6. to exit                         |");
        System.out.println("*---------------------------------------*");
        System.out.print("\nType the number of the transaction you want to select. (Ex: for sorting type 2): ");
        String choose;
        while((choose= scan.nextLine()) != null) {
            if(choose.equals("1")) {
                List_Entities(movielist);
            } else if(choose.equals("2")) {
                sortField(movielist);
            } else if(choose.equals("3")) {
                updatedlist = searchField(movielist);
                List_Entities(updatedlist);
            } else if(choose.equals("4")) {
                List_Column_Names();
            } else if(choose.equals("5")) {
                updatedlist = Filter(movielist);
                List_Entities(updatedlist);
            } else if(choose.equals("6")) {
                System.exit(0);
            } else {
                System.out.println("\nWrong!");
            }

            System.out.println("\n*---------------------------------------*");
            System.out.println("|    1. to list all the entities        |");
            System.out.println("|    2. to sort the entities            |");
            System.out.println("|    3. to search the entities          |");
            System.out.println("|    4. to list column names            |");
            System.out.println("|    5. to filter entities              |");
            System.out.println("|    6. to exit                         |");
            System.out.println("*---------------------------------------*");
            System.out.print("\nType the number of the transaction you want to select. (Ex: for sorting type 2): ");
        }
    }

    public static void List_Entities(ArrayList<Movie> movie) throws IndexOutOfBoundsException {
        System.out.println("\n*---------------------------------------------------*");
        System.out.println("|    1. to list all the fields                      |");
        System.out.println("|    2. to list only the selected fields            |");
        System.out.println("|    3. to list entities based on the range of rows |");
        System.out.println("*---------------------------------------------------*");
        System.out.print("\nType 1, 2, or 3 for listing based on these three option: ");

        String select_list = scan.nextLine();

        if(select_list.equals("1")) {
            int l = 1;
            for(int k = 0; k<100; k++) {
                System.out.print((l) + ". ");
                System.out.println(movie.get(k));
                l++;
            }
            System.out.println("\nTotal number of records: " + (100));
        }
        else if(select_list.equals("2")) {
            System.out.print("Type field names that you want to list (Ex: if you want to see the title and language of the movies, just type (title, language)): ");
            String type = scan.nextLine();
            int n = 1;
            for(int k=0; k<100; k++) {
                System.out.print(n + ". ");
                if(type.toLowerCase().contains("releasedate")) {
                    System.out.print("releaseDate: " + movie.get(k).getReleaseDate() + " ");
                }
                if(type.toLowerCase().contains("title")) {
                    System.out.print("title: "  + movie.get(k).getTitle() + " ");
                }
                if(type.toLowerCase().contains("overview")) {
                    System.out.print("overview: " + movie.get(k).getOverview() + " ");
                }
                if(type.toLowerCase().contains("popularity")) {
                    System.out.print("popularity: " + movie.get(k).getPopularity() + " ");
                }
                if(type.toLowerCase().contains("count"))  {
                    System.out.print("count: " + movie.get(k).getCount() + " ");
                }
                if(type.toLowerCase().contains("average")) {
                    System.out.print("average: " + movie.get(k).getAverage() + " ");
                }
                if(type.toLowerCase().contains("language")) {
                    System.out.print("language: " + movie.get(k).getLanguage() + " ");
                }
                if(type.toLowerCase().contains("genre")) {
                    System.out.print("genre: " + movie.get(k).getGenre() + " ");
                }
                if(type.toLowerCase().contains("posterurl")) {
                    System.out.print("posterUrl: " + movie.get(k).getPosterUrl() + " ");
                }
                n++;
                System.out.println();
            }
            System.out.println("\nTotal number of records: " + (100));
        } else if(select_list.equals("3")) {
            System.out.print("Enter two numbers: lower and upper boundary. (Ex: if you want range is given, 5 100 then just TYPE 5 100): ");
            int number1, number2;
            number1 = scan.nextInt();
            number2 = scan.nextInt();
            for(int i = number1; i < number2; i++) {
                System.out.print(i + ". ");
                System.out.println(movie.get(i).toString());
            }
            int total = number2 - number1;
            System.out.println("\nTotal number of records: " + total);
        } else {
            System.out.println("Wrong!");
        }
    }

    public static void sortField(ArrayList<Movie> movie) {
        System.out.println("*---------------------------------------*");
        System.out.println("|    1. to sort based on releaseDate    |");
        System.out.println("|    2. to sort based on title          |");
        System.out.println("|    3. to sort based on overview       |");
        System.out.println("|    4. to sort based on popularity     |");
        System.out.println("|    5. to sort based on count          |");
        System.out.println("|    6. to sort based on average        |");
        System.out.println("|    7. to sort based on language       |");
        System.out.println("|    8. to sort based on genre          |");
        System.out.println("|    9. to sort based on posterUrl      |");
        System.out.println("*---------------------------------------*");
        System.out.println("\nWhich field do you want to sort? (Ex: if you want to sort count in descending order, then first type 6, then thpe d)");

        String select_sortString = scan.nextLine();

        switch(select_sortString) {
            case "1":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order1 = scan.nextLine();
                if("a".equals(order1))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(x.getYear()-y.getYear() != 0) {
                                return (x.getYear()-y.getYear());
                            }
                            else if(x.getYear()-y.getYear() == 0 && x.getMonth()-y.getMonth() != 0) {
                                return (x.getMonth() - y.getMonth());
                            }
                            return (x.getDay() - y.getDay());
                        }
                    });
                else if("d".equals(order1))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(y.getYear() - x.getYear() != 0) {
                                return (y.getYear() - x.getYear());
                            }
                            else if(y.getYear() - x.getYear() == 0 && y.getMonth() - x.getMonth() != 0) {
                                return (y.getMonth() - x.getMonth());
                            }
                            return (y.getDay() - x.getDay());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "2":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order2 = scan.nextLine();
                if("a".equalsIgnoreCase(order2))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return x.getTitle().compareTo(y.getTitle());
                        }
                    });
                else if("d".equalsIgnoreCase(order2))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return y.getTitle().compareTo(x.getTitle());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "3":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order3 = scan.nextLine();
                if("a".equals(order3))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return x.getOverview().compareTo(y.getOverview());
                        }
                    });
                else if("d".equals(order3))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return y.getOverview().compareTo(x.getOverview());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "4":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order4 = scan.nextLine();
                if("a".equals(order4)) {
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(parseDouble(x.getPopularity()) -  parseDouble(y.getPopularity()) > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else if("d".equals(order4)) {
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(parseDouble(y.getPopularity()) - parseDouble(x.getPopularity()) > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                } else {
                    System.out.println("Wrong!");
                }
                break;
            case "5":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order5 = scan.nextLine();
                if("a".equals(order5)) {
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return parseInt(x.getCount()) - parseInt(y.getCount());
                        }
                    });
                } else if("d".equals(order5)) {
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return parseInt(y.getCount()) - parseInt(x.getCount());
                        }
                    });
                } else {
                    System.out.println("Wrong!");
                }
                break;
            case "6":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order6 = scan.nextLine();
                if("a".equals(order6))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(parseDouble(x.getAverage()) - parseDouble(y.getAverage()) > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                else if("d".equals(order6))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            if(parseDouble(y.getAverage()) - parseDouble(x.getAverage()) > 0.0) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "7":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order7 = scan.nextLine();
                if("a".equals(order7))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return x.getLanguage().compareTo(y.getLanguage());
                        }
                    });
                else if("d".equals(order7))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return y.getLanguage().compareTo(x.getLanguage());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "8":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order8 = scan.nextLine();
                if("a".equals(order8))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return x.getGenre().compareTo(y.getGenre());
                        }
                    });
                else if("d".equals(order8))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return y.getGenre().compareTo(x.getGenre());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            case "9":
                System.out.print("Would you like to sort in ascending or descending order? (If you want to sort in ascending, then type a. If you want to sort in descending order, then type d): ");
                String order9 = scan.nextLine();
                if("a".equals(order9))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return x.getPosterUrl().compareTo(y.getPosterUrl());
                        }
                    });
                else if("d".equals(order9))
                    Collections.sort(movie, new Comparator<Movie>() {
                        public int compare(Movie x, Movie y) {
                            return y.getPosterUrl().compareTo(x.getPosterUrl());
                        }
                    });
                else
                    System.out.println("Wrong!");
                break;
            default:
                System.out.println("Wrong!");
                break;
        }
    }

    public static void List_Column_Names() {
        File file = new File("mymoviedb.csv");
        try(FileReader reader1 = new FileReader(file); BufferedReader reader2 = new BufferedReader(reader1)) {
            String[] line = reader2.readLine().split(",");
            System.out.println("\n\nreleaseDate: " + line[0] + ", title: " + line[1] + ", overview: " + line[2] + ", popularity: " + line[3] + ",  count: " + line[4] + ", average: " + line[5] + ", language: " + line[6] + ", genre: " + line[7] + ", posterUrl: " + line[8]);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static ArrayList<Movie> searchField(ArrayList<Movie> movie) throws IndexOutOfBoundsException {
        System.out.println("\n*-------------------------------------------*");
        System.out.println("|    Type Name of field you want to search  |");
        System.out.print("*-------------------------------------------*    ");

        ArrayList<Movie> searchlist = new ArrayList<>();
        String search = scan.nextLine();
        if(search.equalsIgnoreCase("title") || search.equalsIgnoreCase("posterurl") || search.equalsIgnoreCase("overview") || search.equalsIgnoreCase("language")) {
            if(search.equalsIgnoreCase("overview")) {
                System.out.print("Type contains string: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getOverview().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("title")) {
                System.out.print("Type contains string: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getTitle().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("posterurl")) {
                System.out.print("Type contains string: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getPosterUrl().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("language")) {
                System.out.print("Type contains string: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getLanguage().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(searchlist.size() != 0)  {
                try(FileWriter writer1 = new FileWriter(("list.csv"))) {
                    for(int k = 0; k < searchlist.size(); k++) {
                        writer1.write(searchlist.get(k).toString()+'\n');
                    }
                } catch (Exception e) {
                    System.out.println("Error!");
                }
            } else  {
                System.out.println("Nothing was found.");
            }
        } else if(search.contains("releaseDate") || search.contains("popularity") || search.contains("count") || search.contains("average")) {
            if(search.equalsIgnoreCase("releaseDate")) {
                System.out.print("Type equals part: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getReleaseDate().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("popularity")) {
                System.out.print("Type equals part: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getPopularity().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("count")) {
                System.out.print("Type equals part: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getCount().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(search.equalsIgnoreCase("average")) {
                System.out.print("Type equals part: ");
                String cont = scan.nextLine();
                for(Movie m: movie) {
                    if(m.getAverage().contains(cont)) {
                        searchlist.add(m);
                    }
                }
            }
            if(searchlist.size() != 0) {
                System.out.println("Do you want to store this data in 'list.csv' file? (if yes then type YES, else type NO)");
                String answer = scan.nextLine();
                if(answer.equalsIgnoreCase("YES")) {
                    try(FileWriter fw= new FileWriter(new File("list.csv")); BufferedWriter bw = new BufferedWriter(fw)) {
                        for(int i=0; i<searchlist.size(); i++) {
                            bw.write(searchlist.get(i).toString());
                        }
                    } catch(Exception e) {
                        System.out.println("Error!");
                    }
                }
            }
        } else if(search.equalsIgnoreCase("genre")) {
            System.out.println("\n*--------------------------------------------------*");
            System.out.println("|    Music, Drama, History, Science Fiction, War   |");
            System.out.println("|    Mystery, Thriller, Horror, Crime, Fantasy     |");
            System.out.println("|    Adventure, Romance, Animation, Comedy         |");
            System.out.println("|    TV Movie, Documentary, Action, Family         |");
            System.out.println("*--------------------------------------------------*");
            System.out.print("Type genre you want to search: ");
            String g = scan.nextLine();
            for(Movie m: movie) {
                if(m.getGenre().contains("Music")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Drama")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("History")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Science Fiction")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("War")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Mystery")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Thriller")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Horror")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Crime")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Fantasy")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Adventure")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Romance")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Animation")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Comedy")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("TV Movie")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Documentary")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Action")) {
                    searchlist.add(m);
                } else if(m.getGenre().contains("Family")) {
                    searchlist.add(m);
                }
            }
            if(searchlist.size() != 0) {
                System.out.println("Do you want to store this data in 'list.csv' file? (if yes then type YES, else type NO)");
                String answer = scan.nextLine();
                if(answer.equalsIgnoreCase("YES")) {
                    try(FileWriter fw= new FileWriter(("list.csv")); BufferedWriter bw = new BufferedWriter(fw)) {
                        for(int i=0; i<searchlist.size(); i++) {
                            bw.write(searchlist.get(i).toString() + '\n');
                        }
                        System.out.println("Successfully written!");
                    } catch(Exception e) {
                        System.out.println("Error!");
                    }
                }

            }
        } else {
            System.out.println("Wrong!");
        }
        return searchlist;
    }

    public static ArrayList<Movie> Filter(ArrayList<Movie> movie) throws IllegalArgumentException,SecurityException {
        System.out.print("How many fields would you like to filter? ");
        String n = scan.nextLine();
        int nb = parseInt(n);
        ArrayList<Movie> filter_field = new ArrayList<>();
        for(int k = 0; k <nb; k++) {
            System.out.print("Enter field name: ");
            String filter_parameter = scan.nextLine();
            try {
                Field f = Movie.class.getDeclaredField(filter_parameter);  f.setAccessible(true);
                if(f.getName().equals(filter_parameter) && f.getName().equalsIgnoreCase("title") || f.getName().equalsIgnoreCase("overview") || f.getName().equalsIgnoreCase("genre") || f.getName().equalsIgnoreCase("language") || f.getName().equalsIgnoreCase("posterurl")) {
                    filter_field = FilterString(movie, filter_parameter);
                }
                if(f.getName().equals(filter_parameter) && f.getName().equalsIgnoreCase("releaseDate")) {
                    filter_field = FilterDate(movie, filter_parameter);
                }
                if(f.getName().equals(filter_parameter) && f.getName().equalsIgnoreCase("Vote_Count")) {
                    filter_field = FilterInteger(movie, filter_parameter);
                }
                if(f.getName().equals(filter_parameter) && f.getName().equalsIgnoreCase("Popularity") || f.getName().equalsIgnoreCase("Vote_Average")) {
                    filter_field = FilterDouble(movie, filter_parameter);
                }
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
        if (filter_field.size() != 0) {
            System.out.println("Do you want to store this data in 'list.csv' file? (if yes then type YES, else type NO)");
            String respond = scan.nextLine();
            if (respond.equalsIgnoreCase("YES")) {
                File file = new File("list.csv");
                try (FileWriter fw = new FileWriter(file)) {
                    for (int k = 0; k < filter_field.size(); k++) {
                        fw.write(filter_field.get(k).toString()+'\n');
                    }
                } catch (Exception e) {
                    System.out.println("Error!");
                }
            }
        } else {
            System.out.println("Nothing was found.");
        }
        return filter_field;
    }

    public static ArrayList<Movie> FilterString(ArrayList<Movie> movie, String filter) throws NoSuchFieldException, IllegalAccessException, SecurityException {
        ArrayList<Movie> stringfilter = new ArrayList<>();

        System.out.println("\n*---------------------------------------*");
        System.out.println("|    1. string fields starts with       |");
        System.out.println("|    2. string fields ends with         |");
        System.out.println("|    3. string fields contains          |");
        System.out.println("|    4. string fields null              |");
        System.out.println("*---------------------------------------*");

        System.out.print("\nType 1-4 to filter string fields according to the above options (Ex: if you want to filter movies that starts with Love, then type 1): ");

        String string = scan.nextLine();
        switch(string) {
            case "1":
                System.out.print("\nEnter the starting of fields: ");
                String start = scan.nextLine();
                for(Movie m: movie) {
                    Field field = Movie.class.getDeclaredField(filter); field.setAccessible(true);
                    String value = (String) field.get(m);
                    if(value.startsWith(start)) {
                        stringfilter.add(m);
                    }
                }
                break;
            case "2":
                System.out.print("Enter the ending of fields: ");
                String end = scan.nextLine();
                for(int i=0; i<movie.size(); i++) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String name = (String)f.get(movie.get(i));
                    if(name.endsWith(end)) {
                        stringfilter.add(movie.get(i));
                    }
                }
                break;
            case "3":
                System.out.println("Enter the words that field contains that word (Ex: if you want find in overview friends, then just type friend): ");
                String contain = scan.nextLine();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(value.contains(contain)) {
                        stringfilter.add(m);
                    }
                }
                break;
            case "4":
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(value.length() == 0) {
                        stringfilter.add(m);
                    }
                }
                break;
            default:
                System.out.println("Wrong!");
                break;
        }
        return stringfilter;
    }

    public static ArrayList<Movie> FilterDate(ArrayList<Movie> movie, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException{
        ArrayList<Movie> date_field = new ArrayList<>();

        System.out.println("\n*---------------------------------------*");
        System.out.println("|    1. equal                           |");
        System.out.println("|    2. greater than                    |");
        System.out.println("|    3. less than                       |");
        System.out.println("|    4. greater and equal to            |");
        System.out.println("|    5. less and equal to               |");
        System.out.println("|    6. between                         |");
        System.out.println("|    7. null                            |");
        System.out.println("|    8. in a specific year              |");
        System.out.println("|    9. in a specific month             |");
        System.out.println("|    10. in a specific day              |");
        System.out.println("*---------------------------------------*");
        System.out.print("\nType 1-10 to filter date fields according to the above options (Ex: if you want to filter movies that in 2002 then type 8): ");


        String string = scan.nextLine();

        switch(string) {
            case "1":
                System.out.print("\nEnter the date that you want to filter: ");
                String string1 = scan.nextLine();
                String[] equals = string1.split("/");
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    String[] stringarr1 = value.split("/");
                    if(parseInt(stringarr1[0]) == parseInt(equals[0]) && parseInt(stringarr1[1]) == parseInt(equals[1]) && parseInt(stringarr1[2]) == parseInt(equals[2])) {
                        date_field.add(m);
                    }
                }
                break;
            case "2":
                System.out.print("\nEnter the date that is greater than the date you want to filter: ");
                String string2 = scan.nextLine();
                String[] greaters = string2.split("/");
                int total1 = (365 * parseInt(greaters[2])) + (31 * parseInt(greaters[0]))+(parseInt(greaters[1]));
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    String[] stringarr2 = value.split("/");
                    int total2 = (parseInt(stringarr2[2]) * 365) + (parseInt(stringarr2[0]) * 31) + parseInt(stringarr2[1]);
                    if(total2 > total1) {
                        date_field.add(m);
                    }
                }
                break;
            case "3":
                System.out.print("\nEnter the date that is less than the date you want to filter: ");
                String string3 = scan.nextLine();
                String[] less = string3.split("/");
                int total_3  = (365 * parseInt(less[2])) + (31 * parseInt(less[0])) + parseInt(less[1]);
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String name = (String) f.get(m);
                    String[] stringarr3 = name.split("/");
                    int total3 = (parseInt(stringarr3[2]) * 365) + (parseInt(stringarr3[0]) * 31) + parseInt(stringarr3[1]);
                    if(total3 < total_3) {
                        date_field.add(m);
                    }
                }
                break;
            case "4":
                System.out.print("\nEnter the date that is greater and equal to the date you want to filter: ");
                String string4 = scan.nextLine();
                String[] greaterequals = string4.split("/");
                int total_4 = (365 * parseInt(greaterequals[2])) + (31 * parseInt(greaterequals[0])) + parseInt(greaterequals[1]);
                for(int i=0; i<movie.size(); i++) {
                    Field field = Movie.class.getDeclaredField(filter); field.setAccessible(true);
                    String value = (String) field.get(movie.get(i));
                    String[] string_4 = value.split("/");
                    int total4 = (parseInt(string_4[2]) * 365) + (parseInt(string_4[0]) * 31) + parseInt(string_4[1]);
                    if(total4 >= total_4) {
                        date_field.add(movie.get(i));
                    }
                }
                break;
            case "5":
                System.out.print("\nEnter the date that is less and equal to the date you want to filter: ");
                String date5 = scan.nextLine();
                String[] lessequals = date5.split("/");
                int total_date  = (365 * parseInt(lessequals[2])) + (31 * parseInt(lessequals[0])) + parseInt(lessequals[1]);
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    String[] date_5 = value.split("/");
                    int total = (parseInt(date_5[2]) * 365) + (parseInt(date_5[0]) * 31) + parseInt(date_5[1]);
                    if(total < total_date) {
                        date_field.add(m);
                    }
                }
                break;
            case "6":
                System.out.print("Enter two date: lower and upper boundary. (Ex: if you want filter date is between 5/10/2002 and 3/9/2011, then just type 5/10/2002 3/9/2011): ");
                String datelow = scan.nextLine();
                String[] between1 = datelow.split("/");
                int low  = (365 * parseInt(between1[2])) + (31 * parseInt(between1[0])) + parseInt(between1[1]);
                String datehigh = scan.nextLine();
                String[] between2 = datehigh.split("/");
                int high  = (365 * parseInt(between2[2])) + (31 * parseInt(between2[0])) +  parseInt(between2[1]);
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    String[] date_6 = value.split("/");
                    int total6 = (parseInt(date_6[2]) * 365) + (parseInt(date_6[0]) * 31) + parseInt(date_6[1]);
                    if(total6 > low && high > total6) {
                        date_field.add(m);
                    }
                }
                break;
            case "7":
                for(Movie m: movie) {
                    Field field = Movie.class.getDeclaredField(filter); field.setAccessible(true);
                    String value = (String)field.get(m);
                    if(value.length() == 0) {
                        date_field.add(m);
                    }
                }
                break;
            case "8":
                System.out.print("\nEnter the exact year you want to filter: (Ex: if you want to find movie in 2012, then just type 2012):  ");
                int year = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    String[] date8 = value.split("/");
                    if(year == parseInt(date8[2])) {
                        date_field.add(m);
                    }
                }
                break;
            case "9":
                System.out.print("\nEnter the exact month you want to filter: (Ex: if you want to find movie in March, then just type 3): ");
                int month = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    String[] date9 = value.split("/");
                    if(month == parseInt(date9[0])) {
                        date_field.add(m);
                    }
                }
                break;
            case "10":
                System.out.print("\nEnter the exact day you want to filter: (Ex: if you want to find movie in January 18, then just type 18):  ");
                int day = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String name = (String)f.get(m);
                    String[] date10 = name.split("/");
                    if(day == parseInt(date10[1])) {
                        date_field.add(m);
                    }
                }
                break;
            default:
                System.out.println("Wrong!");
                break;
        }
        return date_field;
    }

    public static ArrayList<Movie> FilterInteger(ArrayList<Movie> movie, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException {
        ArrayList<Movie> list = new ArrayList<>();

        System.out.println("\n*---------------------------------------*");
        System.out.println("|    1. equal                           |");
        System.out.println("|    2. greater than                    |");
        System.out.println("|    3. less than                       |");
        System.out.println("|    4. greater and equal to            |");
        System.out.println("|    5. less and equal to               |");
        System.out.println("|    6. between                         |");
        System.out.println("|    7. null                            |");
        System.out.println("*---------------------------------------*");
        System.out.print("\nType 1-7 to filter integer fields according to the above options (Ex: if you want to filter movies that count (greater than 18) then type 2): ");

        String number = scan.nextLine();

        switch(number) {
            case "1":
                System.out.print("\nEnter the number that is equal to the number you want to filter: ");
                int number1 = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String name = (String) f.get(m);
                    int value = parseInt(name);
                    if(parseInt(String.valueOf(value)) == number1) {
                        list.add(m);
                    }
                }
                break;
            case "2":
                System.out.print("\nEnter the number that is greater than the number you want to filter: ");
                int number2 = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseInt(value) > number2) {
                        list.add(m);
                    }
                }
                break;
            case "3":
                System.out.print("\nEnter the number that is less than the number you want to filter: ");
                int number3 = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseInt(value) < number3) {
                        list.add(m);
                    }
                }
                break;
            case "4":
                System.out.print("\nEnter the number that is greater and equal to the number you want to filter: ");
                int number4 = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter);f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseInt(value) >= number4) {
                        list.add(m);
                    }
                }
                break;
            case "5":
                System.out.print("\nEnter the number that is less and equal to the number you want to filter: ");
                int number5 = scan.nextInt();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseInt(value) <= number5) {
                        list.add(m);
                    }
                }
                break;
            case "6":
                System.out.print("Enter two numbers: lower and upper boundary. (Ex: if you want filter average is between 5.0 and 6.7, then just type 5.0 6.7)");
                double numberlow = scan.nextDouble();
                double numberhigh = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    int num = parseInt(value);
                    if(num > numberlow && num < numberhigh) {
                        list.add(m);
                    }
                }
                break;
            case "7":
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(value.length() == 0) {
                        list.add(m);
                    }
                }
                break;
            default:
                System.out.println("Wrong!");
                break;
        }
        return list;
    }

    public static ArrayList<Movie> FilterDouble(ArrayList<Movie> movie, String filter) throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException {
        ArrayList<Movie> filterlist = new ArrayList<>();

        System.out.println("\n*---------------------------------------*");
        System.out.println("|    1. equal                           |");
        System.out.println("|    2. greater than                    |");
        System.out.println("|    3. less than                       |");
        System.out.println("|    4. greater and equal to            |");
        System.out.println("|    5. less and equal to               |");
        System.out.println("|    6. between                         |");
        System.out.println("|    7. null                            |");
        System.out.println("*---------------------------------------*");
        System.out.println("\nType 1-7 to filter double fields according to the above options (Ex: if you want to filter movies that avarage (less and equal to 6.7) then TYPE 5");

        String number = scan.nextLine();

        switch(number) {
            case "1":
                System.out.print("\nEnter the number that is equal to the number you want to filter: ");
                double number1 = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseDouble(value) == number1) {
                        filterlist.add(m);
                    }
                }
                break;
            case "2":
                System.out.print("\nEnter the number that is greater than the number you want to filter: ");
                double number2 = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    if(parseDouble(value) > number2) {
                        filterlist.add(m);
                    }
                }
                break;
            case "3":
                System.out.print("\nEnter the number that is greater than the number you want to filter: ");
                double number3 = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    if(parseDouble(value) < number3) {
                        filterlist.add(m);
                    }
                }
                break;
            case "4":
                System.out.print("\nEnter the number that is greater and equal to the number you want to filter: ");
                double number4 = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    if(Double.parseDouble(value) >= number4) {
                        filterlist.add(m);
                    }
                }
                break;
            case "5":
                System.out.print("\nEnter the number that is less and equal to the number you want to filter: ");
                double number5 = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    if(parseDouble(value) <= number5) {
                        filterlist.add(m);
                    }
                }
                break;
            case "6":
                System.out.print("Enter two numbers: lower and upper boundary. (Ex: if you want filter average is between 5.0 and 6.7, then just type 5.0 6.7)");
                double numberlow = scan.nextDouble();
                double numberhigh = scan.nextDouble();
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter);  f.setAccessible(true);
                    String value = (String)f.get(m);
                    if(parseDouble(value) > numberlow && parseDouble(value) < numberhigh) {
                        filterlist.add(m);
                    }
                }
                break;
            case "7":
                for(Movie m: movie) {
                    Field f = Movie.class.getDeclaredField(filter); f.setAccessible(true);
                    String value = (String) f.get(m);
                    if(value.length() == 0) {
                        filterlist.add(m);
                    }
                }
                break;
            default:
                System.out.println("Wrong!");
                break;
        }
        return filterlist;
    }
}
