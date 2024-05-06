public class Settings {

    private Skins skin = Skins.DEFAULT;
    private Modes mode = Modes.NOOB;
    private int unlockedLevel = 1;
    private long highestPoints = 0L;
    private String highScoreTime = "00:00:00";
    private Levels highScoreLevel = Levels.LEVEL1;
    private Modes highScoreMode = Modes.GOD;

    //Achievements:
    private boolean NOOBcollected = false;
    private boolean WHATS_WINDING_THEREcollected = false;
    private boolean BEGINNERcollected = false;
    private boolean GOT_THEM_ALLcollected = false;
    private boolean SMELLS_FAMILIARcollected = false;
    private boolean SKIN_ENTHUSIASTcollected = false;
    private boolean YUMMYcollected = false;
    private boolean IAM_GROWING_UPcollected = false;
    private boolean PARENTcollected = false;
    private boolean MASTERcollected = false;
    private boolean DEMIGODcollected = false;
    private boolean SERPENT_GODcollected = false;
    private boolean DIVINITYcollected = false;
    private boolean THE_LONGEST_OF_THEM_ALLcollected = false;
    private boolean COLLECTORcollected = false;
    private boolean COLLECTING_MASTERcollected = false;
    private boolean COLLECTING_GODcollected = false;
    private boolean COLLECTING_ADDICTcollected = false;
    private boolean I_HAVE_NO_LIFEcollected = false;
    private boolean GETTING_BETTERcollected = false;
    private boolean EXPLOITcollected = false;


    public boolean isNOOBcollected() {
        return NOOBcollected;
    }

    public void setNOOBcollected(boolean NOOBcollected) {
        this.NOOBcollected = NOOBcollected;
    }

    public boolean isWHATS_WINDING_THEREcollected() {
        return WHATS_WINDING_THEREcollected;
    }

    public void setWHATS_WINDING_THEREcollected(boolean WHATS_WINDING_THEREcollected) {
        this.WHATS_WINDING_THEREcollected = WHATS_WINDING_THEREcollected;
    }

    public boolean isBEGINNERcollected() {
        return BEGINNERcollected;
    }

    public void setBEGINNERcollected(boolean BEGINNERcollected) {
        this.BEGINNERcollected = BEGINNERcollected;
    }

    public boolean isGOT_THEM_ALLcollected() {
        return GOT_THEM_ALLcollected;
    }

    public void setGOT_THEM_ALLcollected(boolean GOT_THEM_ALLcollected) {
        this.GOT_THEM_ALLcollected = GOT_THEM_ALLcollected;
    }

    public boolean isSMELLS_FAMILIARcollected() {
        return SMELLS_FAMILIARcollected;
    }

    public void setSMELLS_FAMILIARcollected(boolean SMELLS_FAMILIARcollected) {
        this.SMELLS_FAMILIARcollected = SMELLS_FAMILIARcollected;
    }

    public boolean isSKIN_ENTHUSIASTcollected() {
        return SKIN_ENTHUSIASTcollected;
    }

    public void setSKIN_ENTHUSIASTcollected(boolean SKIN_ENTHUSIASTcollected) {
        this.SKIN_ENTHUSIASTcollected = SKIN_ENTHUSIASTcollected;
    }

    public boolean isYUMMYcollected() {
        return YUMMYcollected;
    }

    public void setYUMMYcollected(boolean YUMMYcollected) {
        this.YUMMYcollected = YUMMYcollected;
    }

    public boolean isIAM_GROWING_UPcollected() {
        return IAM_GROWING_UPcollected;
    }

    public void setIAM_GROWING_UPcollected(boolean IAM_GROWING_UPcollected) {
        this.IAM_GROWING_UPcollected = IAM_GROWING_UPcollected;
    }

    public boolean isPARENTcollected() {
        return PARENTcollected;
    }

    public void setPARENTcollected(boolean PARENTcollected) {
        this.PARENTcollected = PARENTcollected;
    }

    public boolean isMASTERcollected() {
        return MASTERcollected;
    }

    public void setMASTERcollected(boolean MASTERcollected) {
        this.MASTERcollected = MASTERcollected;
    }

    public boolean isDEMIGODcollected() {
        return DEMIGODcollected;
    }

    public void setDEMIGODcollected(boolean DEMIGODcollected) {
        this.DEMIGODcollected = DEMIGODcollected;
    }

    public boolean isSERPENT_GODcollected() {
        return SERPENT_GODcollected;
    }

    public void setSERPENT_GODcollected(boolean SERPENT_GODcollected) {
        this.SERPENT_GODcollected = SERPENT_GODcollected;
    }

    public boolean isDIVINITYcollected() {
        return DIVINITYcollected;
    }

    public void setDIVINITYcollected(boolean DIVINITYcollected) {
        this.DIVINITYcollected = DIVINITYcollected;
    }

    public boolean isTHE_LONGEST_OF_THEM_ALLcollected() {
        return THE_LONGEST_OF_THEM_ALLcollected;
    }

    public void setTHE_LONGEST_OF_THEM_ALLcollected(boolean THE_LONGEST_OF_THEM_ALLcollected) {
        this.THE_LONGEST_OF_THEM_ALLcollected = THE_LONGEST_OF_THEM_ALLcollected;
    }

    public boolean isCOLLECTORcollected() {
        return COLLECTORcollected;
    }

    public void setCOLLECTORcollected(boolean COLLECTORcollected) {
        this.COLLECTORcollected = COLLECTORcollected;
    }

    public boolean isCOLLECTING_MASTERcollected() {
        return COLLECTING_MASTERcollected;
    }

    public void setCOLLECTING_MASTERcollected(boolean COLLECTING_MASTERcollected) {
        this.COLLECTING_MASTERcollected = COLLECTING_MASTERcollected;
    }

    public boolean isCOLLECTING_GODcollected() {
        return COLLECTING_GODcollected;
    }

    public void setCOLLECTING_GODcollected(boolean COLLECTING_GODcollected) {
        this.COLLECTING_GODcollected = COLLECTING_GODcollected;
    }

    public boolean isCOLLECTING_ADDICTcollected() {
        return COLLECTING_ADDICTcollected;
    }

    public void setCOLLECTING_ADDICTcollected(boolean COLLECTING_ADDICTcollected) {
        this.COLLECTING_ADDICTcollected = COLLECTING_ADDICTcollected;
    }

    public boolean isI_HAVE_NO_LIFEcollected() {
        return I_HAVE_NO_LIFEcollected;
    }

    public void setI_HAVE_NO_LIFEcollected(boolean i_HAVE_NO_LIFEcollected) {
        I_HAVE_NO_LIFEcollected = i_HAVE_NO_LIFEcollected;
    }

    public boolean isGETTING_BETTERcollected() {
        return GETTING_BETTERcollected;
    }

    public void setGETTING_BETTERcollected(boolean GETTING_BETTERcollected) {
        this.GETTING_BETTERcollected = GETTING_BETTERcollected;
    }

    public boolean isEXPLOITcollected() {
        return EXPLOITcollected;
    }

    public void setEXPLOITcollected(boolean EXPLOITcollected) {
        this.EXPLOITcollected = EXPLOITcollected;
    }

    public String getHighScoreTime() {
        return highScoreTime;
    }

    public void setHighScoreTime(String highScoreTime) {
        this.highScoreTime = highScoreTime;
    }

    public Levels getHighScoreLevel() {
        return highScoreLevel;
    }

    public void setHighScoreLevel(Levels highScoreLevel) {
        this.highScoreLevel = highScoreLevel;
    }

    public long getHighestPoints() {
        return highestPoints;
    }

    public void setHighestPoints(long highestPoints) {
        this.highestPoints = highestPoints;
    }

    public Skins getSkin() {
        return skin;
    }

    public void setSkin(final Skins skin) {
        this.skin = skin;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(final Modes mode) {
        this.mode = mode;
    }

    public int getUnlockedLevel() {
        return unlockedLevel;
    }

    public void setUnlockedLevel(int unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }

    public Modes getHighScoreMode() {
        return highScoreMode;
    }

    public void setHighScoreMode(Modes highScoreMode) {
        this.highScoreMode = highScoreMode;
    }
}
