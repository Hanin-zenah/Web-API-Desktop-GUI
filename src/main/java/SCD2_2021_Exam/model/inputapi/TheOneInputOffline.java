package SCD2_2021_Exam.model.inputapi;

public class TheOneInputOffline implements TheOneInput {
    /**
     * Sets the access token to the API.<br><br>
     *
     * @param token The access token.
     */
    @Override
    public void setToken(String token) {}

    /**
     * Lists the available characters in the API. <br><br>
     *
     * @return A dummy string for some available characters.
     */
    @Override
    public String listAvailableCharacters() {
        return "ID: 5cd99d4bde30eff6ebccfbbe\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Female\n" +
                "birth: \n" +
                "spouse: Belemir\n" +
                "death: \n" +
                "realm: \n" +
                "hair: \n" +
                "name: Adanel\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Adanel\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbbf\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Male\n" +
                "birth: Before ,TA 1944\n" +
                "spouse: \n" +
                "death: Late ,Third Age\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Adrahil I\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Adrahil_I\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc0\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Male\n" +
                "birth: TA 2917\n" +
                "spouse: Unnamed wife\n" +
                "death: TA 3010\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Adrahil II\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Adrahil_II\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc1\n" +
                "height: \n" +
                "race: Elf\n" +
                "gender: Male\n" +
                "birth: YT during the ,Noontide of Valinor\n" +
                "spouse: Loved ,Andreth but remained unmarried\n" +
                "death: FA 455\n" +
                "realm: \n" +
                "hair: Golden\n" +
                "name: Aegnor\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Aegnor\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc2\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Female\n" +
                "birth: Mid ,First Age\n" +
                "spouse: Brodda\n" +
                "death: FA 495\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Aerin\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Aerin\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc3\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Female\n" +
                "birth: Between ,SA 700, and ,SA 750\n" +
                "spouse: Orchaldor\n" +
                "death: Early ,Second Age\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Ailinel\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Ailinel\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc4\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Male\n" +
                "birth: TA 2827\n" +
                "spouse: Unnamed wife\n" +
                "death: TA 2932\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Aglahad\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Aglahad\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc5\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Male\n" +
                "birth: Late ,First Age\n" +
                "spouse: None known\n" +
                "death: FA 489\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Algund\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Algund\n" +
                "\n" +
                "ID: 5cd99d4bde30eff6ebccfbc6\n" +
                "height: \n" +
                "race: Human\n" +
                "gender: Female\n" +
                "birth: Between ,SA 700, and ,SA 750\n" +
                "spouse: \n" +
                "death: Early ,Second Age\n" +
                "realm: \n" +
                "hair: \n" +
                "name: Almiel\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Almiel";
    }

    /**
     * Lists the available quotes of a given character from the API <br><br>
     *
     * @param characterID The character ID.
     * @return A dummy string representing the available quotes for some character.
     */
    @Override
    public String listAvailableQuotes(String characterID) {
        if(characterID == null) {
            System.out.println("character id cannot be null");
            return null;
        }
        return "ID: 5cd96e05de30eff6ebccf099\n" +
                "dialog: Good Evening little masters. What can I do for you ?     If you're looking for accomodation, we've got some nice, cosy hobbit sized     rooms available. Always proud to cater to the little folk, Mr err... ?\n" +
                "movie: 5cd95395de30eff6ebccde5c\n" +
                "character: 5cdbdecb6dc0baeae48cfaa5\n" +
                "\n" +
                "ID: 5cd96e05de30eff6ebccf09b\n" +
                "dialog: Underhill , yes..\n" +
                "movie: 5cd95395de30eff6ebccde5c\n" +
                "character: 5cdbdecb6dc0baeae48cfaa5\n" +
                "\n" +
                "ID: 5cd96e05de30eff6ebccf0a6\n" +
                "dialog: Gandalf ? ,Gandalf ? Oh yes! I remember! Elderly chap! Big grey     beard, pointy hat. , Not seen     him for six months. \n" +
                "movie: 5cd95395de30eff6ebccde5c\n" +
                "character: 5cdbdecb6dc0baeae48cfaa5\n" +
                "\n" +
                "ID: 5cd96e05de30eff6ebccf0d1\n" +
                "dialog: He's one of them rangers. They're dangerous folk they are.     Wandering the wilds. What his right name is, I've never heard, but     round here, he's known as Strider.\n" +
                "movie: 5cd95395de30eff6ebccde5c\n" +
                "character: 5cdbdecb6dc0baeae48cfaa5\n" +
                "\ntotal: 4,limit: 1000,offset: 0,page: 1,pages: 1";
    }

    /**
     * Gets the information on a selected character <br><br>
     *
     * @param characterID The character ID to get information on.
     * @return Dummy available information on some random character.
     */
    @Override
    public String getCharacterInfo(String characterID) {
        return "ID: 5cd99d4bde30eff6ebccfc3f\n" +
                "height: \n" +
                "race: Hobbit\n" +
                "gender: Female\n" +
                "birth: TA 2950\n" +
                "spouse: Griffo Boffin\n" +
                "death: \n" +
                "realm: \n" +
                "hair: \n" +
                "name: Daisy (Baggins) Boffin\n" +
                "wikiUrl: http: //lotr.wikia.com//wiki/Daisy_(Baggins)_Boffin";
    }
}