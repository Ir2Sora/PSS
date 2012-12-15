package entity;

public enum Status {
    
    NEW("Новая"),
    
    RequestedPeerRewiew("Запрошена экспертная оценка"),
    
    ReceivedPeerRewiew("Получена экспертная оценка"),
    
    OnExpertise("На экспертизе"),
    
    RequireImprovement("На доработку инициатору"),
    
    Improved("Доработана"),
    
    Adoption("Внедрение"),
    
    Adopted("Внедрена"),
    
    Rejected("Отклонена");
    
    private String description;
    
    Status(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public Status[] getStatusesForSuggestion(){
        Status[] result = new Status[7];
        result[0] = Status.NEW;
        result[1] = Status.OnExpertise;
        result[2] = Status.RequireImprovement;
        result[3] = Status.Improved;
        result[4] = Status.Adopted;
        result[5] = Status.Adoption;
        result[6] = Status.Rejected;
        return result;
    }
    
    public Status[] getStatusesForDirection(){
        Status[] result = new Status[5];
        result[0] = Status.RequestedPeerRewiew;
        result[1] = Status.ReceivedPeerRewiew;
        result[2] = Status.Adopted;
        result[3] = Status.Adoption;
        result[4] = Status.Rejected;
        return result;
    }
}
