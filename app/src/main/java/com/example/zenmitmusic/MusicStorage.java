package com.example.zenmitmusic;

import java.util.ArrayList;

public class MusicStorage {

    MusicStorage() {

        TopTrackList();
        AkcentMusicList();
        CarlasMusicList();
        VescanMusicList();
        FaydeeMusicList();
        TopArtistesList();
        AllMusicList();
    }

    private static final ArrayList<Music> topSongs = new ArrayList<>();

    static ArrayList<Music> getTopSongs() {

        return topSongs;
    }
    private void TopTrackList() {
        Music akcentList = new Music("A1001", "Kamelia", "Akcent",
                "2FAdrian%20Sina%20-%20Tu%20m-ai%20dat%20gata%20(Original%20Radio%20edit)%20%5B%20rostfery%20%26%20www.dolomp3.org%20%5D.mp3?alt=media&token=b6794b5e-5004-4674-9db3-4fd1f89f3336",
                R.drawable.akcent);

        Music carlasList = new Music("C1001", "Imperfect", "Carla's Dreams",
                "2FCarla's%20Dreams%20-%20Imperfect%20_%20Official%20Video.mp3?alt=media&token=97d6594d-4eca-4cb1-97ff-15bb0544951e", "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music vescanList = new Music("V1001", "Ce-o Fie,ce O Fi", "Vescan",
                "2FVescan%20-%20Ce-o%20Fi...%20O%20Fi.mp3?alt=media&token=980e9240-fff9-421c-a1a9-a95b2d7b3622",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");

        Music faydeeList = new Music("F1001", "Can't Let Go", "Faydee",
                "2FFaydee%20-%20Can%20t%20Let%20Go%20(Radio%20Edit).mp3?alt=media&token=427f3ce5-dc91-415b-ac52-311ed6974ba3",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");

        akcentList.setSongList(topSongs);
        carlasList.setSongList(topSongs);
        vescanList.setSongList(topSongs);
        faydeeList.setSongList(topSongs);
        topSongs.add(akcentList);
        topSongs.add(carlasList);
        topSongs.add(vescanList);
        topSongs.add(faydeeList);

    }

    private static final ArrayList<Music> topArtistes = new ArrayList<>();

    static ArrayList<Music> getTopArtistes() {
        return topArtistes;
    }
    private void TopArtistesList() {

        Music akcent = new Music("S1003", "", "Akcent",
                "2FAkcent%20feat%20Lidia%20Buble%20%26%20DDY%20Nunes%20-%20Kamelia%20(%20Radio%20Edit%202014%20)%20(1).mp3?alt=media&token=cd76fcbd-116e-4c46-8c28-52275bb86fa1",
                R.drawable.akcent);

        Music carlas = new Music("S1004", "", "Carla's Dreams",
                "2FCarla's%20Dreams%20-%20Imperfect%20_%20Official%20Video.mp3?alt=media&token=97d6594d-4eca-4cb1-97ff-15bb0544951e",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fcarla-s-dreams.jpg?alt=media&token=fdf85abb-287e-4b92-a876-37b705c3a919");

        Music vescan = new Music("S1006", "", "Vescan",
                "2FVescan%20-%20Ce-o%20Fi...%20O%20Fi.mp3?alt=media&token=980e9240-fff9-421c-a1a9-a95b2d7b3622",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan.jpg?alt=media&token=ac5f0bea-2338-4a22-86b8-31d65afed65c");

        Music faydee = new Music("S1007", "", "Faydee",
                "2FFaydee%20-%20Can%20t%20Let%20Go%20(Radio%20Edit).mp3?alt=media&token=427f3ce5-dc91-415b-ac52-311ed6974ba3",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Ffaydee2.jpeg?alt=media&token=6d47d43b-d355-4ce8-a4de-af239334994e");

        akcent.setSongList(AkcentMusic);
        carlas.setSongList(CarlasMusic);
        vescan.setSongList(VescanMusic);
        faydee.setSongList(FaydeeMusic);
        topArtistes.add(akcent);
        topArtistes.add(carlas);
        topArtistes.add(vescan);
        topArtistes.add(faydee);

    }


    private final ArrayList<Music> AkcentMusic = new ArrayList<>();

    private void AkcentMusicList() {
        Music kamelia = new Music("A1001", "Kamelia", "Akcent",
                "2FAkcent%20feat%20Lidia%20Buble%20%26%20DDY%20Nunes%20-%20Kamelia%20(%20Radio%20Edit%202014%20)%20(1).mp3?alt=media&token=cd76fcbd-116e-4c46-8c28-52275bb86fa1",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music tu = new Music("A1002", "Tu m-ai dat gata ", "Akcent",
                "2FAdrian%20Sina%20-%20Tu%20m-ai%20dat%20gata%20(Original%20Radio%20edit)%20%5B%20rostfery%20%26%20www.dolomp3.org%20%5D.mp3?alt=media&token=b6794b5e-5004-4674-9db3-4fd1f89f3336",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music zile = new Music("A1003", "Zile Bune Zile Rele.", "Akcent",
                "2FAdrian_Sina_feat_Cojo_Zile_Bune_Zile_Rele.mp3?alt=media&token=435f895b-5582-4524-8172-f0adfb401690",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music dilema = new Music("A1004", "Dilemma.", "Akcent",
                "2FAkcent_feat_Meriem_Dilemma.mp3?alt=media&token=013be3cf-2783-49fb-a90e-ce96ba4d8c35",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music heart = new Music("A1005", "Heart Attack", "Akcent",
                "2FAkcent%20%20-%20Heart%20Attack.mp3?alt=media&token=1d8a31ec-547e-4087-b24e-9272dc9cd114",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music without = new Music("A1006", "Without You", "Akcent",
                "2FAkcent%20-%20Without%20You.mp3?alt=media&token=96fb17c3-3574-4d39-8b40-fa7d53fbbfd5",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music you = new Music("A1007", "You dont know my love", "Akcent",
                "2FAkcent%20-%20You%20dont%20know%20my%20love.mp3?alt=media&token=6d6a60bf-b212-4fad-a178-0a0cc18d923b",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music mirror = new Music("A1008", "Mirror", "Akcent",
                "2FAkcent-%20Mirror.mp3?alt=media&token=2374393f-0004-48cf-a48f-08fc897a2d6c",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music bohema = new Music("A1009", "Bohema", "Akcent",
                "2FAkcent%20-%20Bohema.mp3?alt=media&token=4f754ef9-8164-4a2e-8642-bc1745a9973a",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");
        Music deep = new Music("A1010", "Deep In Your Eyes", "Akcent",
                "2FAkcent%20-%20Deep%20In%20Your%20Eyes.mp3?alt=media&token=e1271198-2b82-403c-867b-ba87633b9f26",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fakcent.jpg?alt=media&token=1b80b61a-3a2e-4ba8-b243-449c4a271079");

        kamelia.setSongList(AkcentMusic);
        tu.setSongList(AkcentMusic);
        zile.setSongList(AkcentMusic);
        dilema.setSongList(AkcentMusic);
        heart.setSongList(AkcentMusic);
        without.setSongList(AkcentMusic);
        you.setSongList(AkcentMusic);
        mirror.setSongList(AkcentMusic);
        bohema.setSongList(AkcentMusic);
        deep.setSongList(AkcentMusic);

        AkcentMusic.add(kamelia);
        AkcentMusic.add(tu);
        AkcentMusic.add(zile);
        AkcentMusic.add(dilema);
        AkcentMusic.add(heart);
        AkcentMusic.add(without);
        AkcentMusic.add(you);
        AkcentMusic.add(mirror);
        AkcentMusic.add(bohema);
        AkcentMusic.add(deep);
    }

    private final ArrayList<Music> CarlasMusic = new ArrayList<>();

    private void CarlasMusicList() {

        Music imperfect = new Music("C1001", "Imperfect", "Carla's Dreams",
                "2FCarla's%20Dreams%20-%20Imperfect%20_%20Official%20Video.mp3?alt=media&token=97d6594d-4eca-4cb1-97ff-15bb0544951e",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music acele = new Music("C1002", "Acele", "Carla's Dreams",
                "2FCarla's%20Dreams%20-%20Acele%20(DJ%20Asher%20Remix)%20(Official%20Video).mp3?alt=media&token=6b83c0e9-070f-4843-94d9-39bfcbe759c3",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music inimi = new Music("C1003", " 3 Inimi", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%203%20Inimi.mp3?alt=media&token=2c734bfe-52d1-451e-ba93-f48d0da5c26f",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music baila = new Music("C1004", "Baila Conmigo", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Baila%20Conmigo.mp3?alt=media&token=55c9735c-da38-4ecc-87e9-2ccb8e6517dd",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music  bambolina = new Music("C1005", " Bambolina", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Bambolina.mp3?alt=media&token=5eb8ccbf-1b28-458c-9633-7a88e59f1de8",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music frica = new Music("C1006", "Frica", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Frica.mp3?alt=media&token=3ae17c77-adbc-4aef-a124-da5ae955d3c7",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music karma = new Music("C1007", "Karma", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Karma.mp3?alt=media&token=32ce08cd-fadf-47ce-868a-dea64d4936ea",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music lacrimi = new Music("C1008", "Lacrimi si Pumni in Pereti", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Lacrimi%20si%20Pumni%20in%20Pereti.mp3?alt=media&token=1c2f36e1-e84d-43cc-8cb9-1b737810f3a4",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music praf= new Music("C1009", "Praf de stele", "Carla's Dreams",
                "2FCarlas%20Dreams%20-%20Praf%20de%20stele.mp3?alt=media&token=6373b584-757d-49c1-9f0d-92931a61ea0e",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        Music anxietate = new Music("C1010", "Anxietate", "Carla's Dreams",
                "2FCarlas%20Dreams-%20Anxietate.mp3?alt=media&token=9dd37d41-46fd-44e8-9f8b-677bd2848681",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2FCarlas2.jpg?alt=media&token=666ccafe-1a36-4bc1-a499-a0f66fca42c3");

        imperfect.setSongList(CarlasMusic);
        acele.setSongList(CarlasMusic);
        inimi.setSongList(CarlasMusic);
        baila.setSongList(CarlasMusic);
        bambolina.setSongList(CarlasMusic);
        frica.setSongList(CarlasMusic);
        karma.setSongList(CarlasMusic);
        lacrimi.setSongList(CarlasMusic);
        praf.setSongList(CarlasMusic);
        anxietate.setSongList(CarlasMusic);

        CarlasMusic.add(imperfect);
        CarlasMusic.add(acele);
        CarlasMusic.add(inimi);
        CarlasMusic.add(baila);
        CarlasMusic.add(bambolina);
        CarlasMusic.add(frica);
        CarlasMusic.add(karma);
        CarlasMusic.add(lacrimi);
        CarlasMusic.add(praf);
        CarlasMusic.add(anxietate);

    }

    private final ArrayList<Music> VescanMusic = new ArrayList<>();

    private void VescanMusicList() {
        Music ce = new Music("V1001", "Ce-o Fie,ce O Fi", "Vescan",
                "2FVescan%20-%20Ce-o%20Fi...%20O%20Fi.mp3?alt=media&token=980e9240-fff9-421c-a1a9-a95b2d7b3622",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music tacerea = new Music("V1002", "Tacerea", "Vescan",
                "2FVescan%20-%20Tacerea.mp3?alt=media&token=64ece267-4cbc-4c86-9936-6d612f33d2a7",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music acasa = new Music("V1003", "Te intorci acasa", "Vescan",
                "2FVescan%20-%20Te%20intorci%20acasa.mp3?alt=media&token=26dd0924-3947-4572-b8fa-edc75c189b7a",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music tic = new Music("V1004", "Tic-Tac", "Vescan",
                "2FVescan%20-%20Tic-Tac.mp3?alt=media&token=bacc801b-f898-48c7-b4fa-78e83ee67293",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music stiu = new Music("V1005", "Le Stiu Pe Astea Ca Tine", "Vescan",
                "2FVescan%20cu%20Doddy%20-%20Le%20Stiu%20Pe%20Astea%20Ca%20Tine%20(Zippy)%20by%20www.RadioFLy.ws%20-%20Copy.mp3?alt=media&token=1e0a644e-9239-4507-951f-d3e64b5c6be3",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music dreapta = new Music("V1006", " In Dreapta Ta", "Vescan",
                "2FVescan%20feat.%20Alina%20Eremia%20-%20In%20Dreapta%20Ta%20(Official%20Single)%20%5B%20%40%20Rostfery%20%26%20www.Dobridor.Hi2.Ro%20%5D.mp3?alt=media&token=b334bb0e-54d2-45ba-9dd3-834f06505112",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music piesa = new Music("V1007", "Piesa mea preferata", "Vescan",
                "2FVescan%20ft%20Kamelia%20-Piesa%20mea%20preferata.%20By%20LaurentiuRappstyle.mp3?alt=media&token=1d61e8dc-6b44-4027-857c-cddf63db483e",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music las = new Music("V1008", "Las-o", "Vescan",
                "2FVescan-%20Las-o.mp3?alt=media&token=53b2b939-6080-4beb-bb49-3773454f3011",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music necunoscuti = new Music("V1009", "Ca Doi Necunoscuti", "Vescan",
                "2FVescan-Ca%20Doi%20Necunoscuti.mp3?alt=media&token=ba0e1487-e877-4fe7-9cfe-b523b0ae564f",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");
        Music dulce = new Music("V1010", "Dulce Otrava", "Vescan",
                "2FRaluka%20feat.%20Killa%20Fonic%20-%20Dulce%20Otrava%20(Official%20Video).mp3?alt=media&token=8f0b1726-15a0-48ce-9277-b9453c93e784",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fvescan2.jpg?alt=media&token=ee5123a9-cc93-4927-bfc5-be46f1619ff6");

        ce.setSongList(VescanMusic);
        tacerea.setSongList(VescanMusic);
        acasa.setSongList(VescanMusic);
        tic.setSongList(VescanMusic);
        stiu.setSongList(VescanMusic);
        dreapta.setSongList(VescanMusic);
        piesa.setSongList(VescanMusic);
        las.setSongList(VescanMusic);
        necunoscuti.setSongList(VescanMusic);
        dulce.setSongList(VescanMusic);

        VescanMusic.add(ce);
        VescanMusic.add(tacerea);
        VescanMusic.add(acasa);
        VescanMusic.add(tic);
        VescanMusic.add(stiu);
        VescanMusic.add(dreapta);
        VescanMusic.add(piesa);
        VescanMusic.add(las);
        VescanMusic.add(necunoscuti);
        VescanMusic.add(dulce);
    }

    private final ArrayList<Music> FaydeeMusic = new ArrayList<>();

    private void FaydeeMusicList() {
        Music cant = new Music("F1001", "Can't Let Go", "Faydee",
                "2FFaydee%20-%20Can%20t%20Let%20Go%20(Radio%20Edit).mp3?alt=media&token=427f3ce5-dc91-415b-ac52-311ed6974ba3",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music call = new Music("F1002", "Call Me Habibi", "Faydee",
                "2FFaydee%20-%20Call%20Me%20Habibi.mp3?alt=media&token=3f5a16b1-a2c5-4f57-8c0a-28ea11085889",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music crazy = new Music("F1003", "Crazy", "Faydee",
                "2FFaydee%20-%20Crazy.mp3?alt=media&token=776ee324-2f8b-4ca5-a3a4-e8eb86f49377",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music dont = new Music("F1004", "Dont hang up", "Faydee",
                "2FFaydee%20-%20Dont%20hang%20up.mp3?alt=media&token=f8f370dc-bd48-48a5-a2ec-59e63044752d",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music enchante = new Music("F1005", "Enchante", "Faydee",
                "2FFaydee%20-%20Enchante.mp3?alt=media&token=92df02e8-92ac-4058-9a60-f5d6a220d849",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music arms = new Music("F1006", " In Your Arms Tonight", "Faydee",
                "2FFaydee%20-%20In%20Your%20Arms%20Tonight.mp3?alt=media&token=f3e9461e-a327-45b6-8535-b82dae79d9a7",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music lost = new Music("F1007", " Lost In These", "Faydee",
                "2FFaydee%20-%20Lost%20In%20These.mp3?alt=media&token=6965a69e-7ac5-430b-9cea-022cb2133a03",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music move= new Music("F1008", " Move On 2015 ", "Faydee",
                "2FFaydee%20-%20Move%20On%202015%20(Cest%20La%20Vie)%20(Originala).mp3?alt=media&token=a920f6f7-9baa-43cf-9a8a-5107760f3aaa",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music without = new Music("F1009", " Without You", "Faydee",
                "2FFaydee%20-%20Without%20You.mp3?alt=media&token=cab9c88b-d8b2-45be-9b52-37f05f1548fe",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");
        Music laugh = new Music("F1010", " Laugh Till You Crye", "Faydee",
                "2FFaydee-Laugh%20Till%20You%20Cry.mp3?alt=media&token=5263ad7d-4e75-43a6-bbd8-2a0299666f59",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs3.jpg?alt=media&token=3572f1f8-0fde-496c-97f3-12dcfd2c3e6e");

        cant.setSongList(FaydeeMusic);
        call.setSongList(FaydeeMusic);
        crazy.setSongList(FaydeeMusic);
        dont.setSongList(FaydeeMusic);
        enchante.setSongList(FaydeeMusic);
        arms.setSongList(FaydeeMusic);
        lost.setSongList(FaydeeMusic);
        move.setSongList(FaydeeMusic);
        without.setSongList(FaydeeMusic);
        laugh.setSongList(FaydeeMusic);

        FaydeeMusic.add(cant);
        FaydeeMusic.add(call);
        FaydeeMusic.add(crazy);
        FaydeeMusic.add(dont);
        FaydeeMusic.add(enchante);
        FaydeeMusic.add(arms);
        FaydeeMusic.add(lost);
        FaydeeMusic.add(move);
        FaydeeMusic.add(without);
        FaydeeMusic.add(laugh);

    }

    private static final ArrayList<Music> AllMusic = new ArrayList<>();

    static ArrayList<Music> getAllSongs() {
        return AllMusic;
    }

    private void AllMusicList() {
        Music luminile = new Music("S1001", "Cand Luminile Se Sting", "Alina Eremia",
                "2FAlina%20Eremia%20-%20Cand%20Luminile%20Se%20Sting%202014%20(Original%20Radio%20Edit)%20%5B%20%40%20DuMa%20%26%20wWw.VitanClub.Net%20%5D.mp3?alt=media&token=5c71ac21-6081-4ae5-a2db-62b9383c0000",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Feremia.jpg?alt=media&token=7f4324bb-1dc1-4f21-861d-2a71bc076eb2");
        Music you = new Music("S1005", "Played_You", "Alina Eremia",
                "2FAlina_Eremia_Played_You.mp3?alt=media&token=3b129887-ffad-4965-b5c2-b70e1640caed",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Feremia.jpg?alt=media&token=7f4324bb-1dc1-4f21-861d-2a71bc076eb2");
        Music loca = new Music("S1008", "Chica Loca", "Antonia",
                "2FAntonia%20-%20Chica%20Loca%20(Official%20Music)%20(www.lordmusic.ru).mp3?alt=media&token=baeda3b2-5afb-447f-9eab-a2b235efbd0c",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fantonia.jpg?alt=media&token=c22fa190-eac7-43c1-92cf-e5e43dfd32e7");
        Music wild = new Music("S1002", " Wild Horses", "Antonia",
                "2FAntonia%20feat.%20Jay%20Sean%20-%20Wild%20Horses%20(Originala%20Radio%20Edit)%20(Mp3tube.Ro).mp3?alt=media&token=ea358d6a-495a-4d27-8e1d-755fd793f425",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fantonia.jpg?alt=media&token=c22fa190-eac7-43c1-92cf-e5e43dfd32e7");
        Music hey = new Music("S1005", "Hey Brother", "Avicii ",
                "2FAvicii%20-%20Hey%20Brother%20(Radio%20Edit)%20%5B%20www.Clip32.Net%20%5D.mp3?alt=media&token=495d92b3-3a3a-461e-ae03-9e7744323c44",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Favicii.jpg?alt=media&token=3c73bf61-3ca1-4582-8ec0-9ad5a9905628");
        Music wake = new Music("S1009", " Wake Me Up", "Avicii ",
                "2FAvicii%20-%20Wake%20Me%20Up.mp3?alt=media&token=3b042911-c796-4acc-9281-f8cc86a5e9dd",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Favicii.jpg?alt=media&token=3c73bf61-3ca1-4582-8ec0-9ad5a9905628");
        Music youA = new Music("S1010", " You", "Avicii",
                "2FAvicii_X_You.mp3?alt=media&token=4d50cdaa-12f2-4fa4-b795-ffda7005debf",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Favicii.jpg?alt=media&token=3c73bf61-3ca1-4582-8ec0-9ad5a9905628");
        Music burn = new Music("S1013", " Burn", "Ellie Goulding ",
                "2FEllie%20Goulding%20-%20Burn%5Bmuzica.online4.org%5D.mp3?alt=media&token=1dd9312c-4e15-40c5-a05b-0094b56f6ae9",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs5.png?alt=media&token=2a187ab7-c344-4fba-b329-538cc9056ac8");
        Music love = new Music("S1011", " Love_Me_Like_You_D", "Ellie Goulding ",
                "2FEllie_Goulding_Love_Me_Like_You_Do.mp3?alt=media&token=404b98a2-b02e-4547-915a-f5d1fb12e17f",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fs5.png?alt=media&token=2a187ab7-c344-4fba-b329-538cc9056ac8");
        Music solo = new Music("S1012", "  Flying Solo", "Lora",
                "2FLora%20feat.%20Omar%20-%20Flying%20Solo%20(Original%20Radio%20Edit)%20by%20Alecs'x%20%40%20www.muzicafilme.net.mp3?alt=media&token=aa219057-c91e-46e5-8060-8b50a8f96a81",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Flora.jpg?alt=media&token=2489bda3-5aa3-45eb-bac6-0dc92422e7a2");
        Music royals = new Music("S1014", " Royals", "Lora",
                "2FLorde_Royals.mp3?alt=media&token=dec1ace9-091f-4967-a19e-32d592ebd9f9",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Flora.jpg?alt=media&token=2489bda3-5aa3-45eb-bac6-0dc92422e7a2");
        Music lose = new Music("S1016", "Lose Yourself", "Major Lazer",
                "2FMajor%20Lazer%20-%20Lose%20Yourself%20feat.%20Moska%20RDX%20(www.muzicaoriginala.net™2014™bY%20dJ%20aLLy™).mp3?alt=media&token=c7d8e222-481d-473b-9fca-0053f410e4c7",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fmajor.jpg?alt=media&token=23a74b20-cf29-42b5-9b1b-bfd66bdc548d");
        Music watch = new Music("S1015", "Watch_Out_For_This_Bumaye", "Major Lazer",
                "2FMajor_Lazer_Ft_Busy_Signal_The_Flexican_And_FS_Green_Watch_Out_For_This_Bumaye.mp3?alt=media&token=4c5d51db-4f07-46a4-98bb-86e7b7b4fbee",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fmajor.jpg?alt=media&token=23a74b20-cf29-42b5-9b1b-bfd66bdc548d");
        Music lover = new Music("S1017", "Lover", "INNA",
                "2FINNA%20-%20Lover%20(Radio%20Edit).mp3?alt=media&token=080db173-4186-4f22-820e-81da6ee60d37",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Finna.jpg?alt=media&token=e9dbb994-7d2f-49a8-8e52-25431d5054e2");
        Music cuvinte = new Music("S1018", "Cuvintele tale", "Nicole Cherry",
                "2FNicole%20Cherry%20-%20Cuvintele%20tale%20(%20Original%20Radio%20Edit%20)%20%5B%20AlegeMuzica.Info%20%5D.mp3?alt=media&token=d3e2993c-c386-4e22-9d94-4912aae3785f",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fnicole.png?alt=media&token=3180a4dd-983a-4f07-a4e5-a5873c494552");
        Music zbor = new Music("S1019", "Zbor", "Raluka",
                "2FRaluka%20-%20Zbor%20(%20Original%20Radio%20Edit%20)%20www.danparfum.net.mp3?alt=media&token=80b08145-bfbb-461c-b688-491fdc264032",
                "https://firebasestorage.googleapis.com/v0/b/zenmit-music-d5531.appspot.com/o/Image%2Fraluka.jpg?alt=media&token=99c548a8-b8ad-496d-b4d4-8b2efab2b23d");

        AllMusic.addAll(FaydeeMusic);
        AllMusic.addAll(VescanMusic);
        AllMusic.addAll(CarlasMusic);
        AllMusic.addAll(AkcentMusic);
        AllMusic.add(luminile);
        AllMusic.add(you);
        AllMusic.add(loca);
        AllMusic.add(wild);
        AllMusic.add(hey);
        AllMusic.add(wake);
        AllMusic.add(youA);
        AllMusic.add(burn);
        AllMusic.add(love);
        AllMusic.add(solo);
        AllMusic.add(royals);
        AllMusic.add(lose);
        AllMusic.add(watch);
        AllMusic.add(lover);
        AllMusic.add(cuvinte);
        AllMusic.add(zbor);
    }
}
