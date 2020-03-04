//Rachana Kotamraju and Sujit Molleti

package sample;

public class SongDetail {

    String artist;
    String album;
    String year;
    String song;


    public SongDetail(String artist, String album, String year, String song){
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.song = song;
    }

    public String returnStringID(){

        if(album.length() == 0){
            album = " ";
        }
        if(year.length() == 0){
            year = " ";
        }

        return song+",&"+artist+",&"+album+",&"+year+",&";
    }

    public String toString(){
        return song+" by "+artist;
    }
}
