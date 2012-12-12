package dao;

public enum Status {
    
    NEW("Новая"),
    
    RequestedPeerRewiew("Запрошена экспертная оценка"),
    
    ReceivedPeerRewiew("Получена экспертная оценка"),
    
    OnExpertise("На экспертизе"),
    
    RequireImprovement("На доработку инициатору"),
    
    Improved("Доработана"),
    
    Adoption("Внедрение"),
    
    Adopted("Внедрена"),
    
    Rejected("Отклонена"),
    
    Registered("Зарегистрирована"),
    
    Recommended("Рекомендованна");
    
    private String description;
    
    public static Status getStatus(String status){
        if (status.equals("NEW"))
            return Status.NEW;
        else if (status.equals("RequestedPeerRewiew"))
            return Status.RequestedPeerRewiew;
        else if (status.equals("ReceivedPeerRewiew"))
            return Status.ReceivedPeerRewiew;
        else if (status.equals("OnExpertise"))
            return Status.OnExpertise;
        else if (status.equals("RequireImprovement"))
            return Status.RequireImprovement;
        else if (status.equals("Improved"))
            return Status.Improved;
        else if (status.equals("Adoption"))
            return Status.Adoption;
        else if (status.equals("Adopted"))
            return Status.Adopted;
        else if (status.equals("Rejected"))
            return Status.Rejected;
        else if (status.equals("Registered"))
            return Status.Registered;
        else
            return Status.Recommended;
    }
    
    Status(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public Status[] getStatusesForSuggestion(){
        Status[] result = new Status[9];
        result[0] = Status.NEW;
        result[1] = Status.OnExpertise;
        result[2] = Status.RequireImprovement;
        result[3] = Status.Improved;
        result[4] = Status.Adopted;
        result[5] = Status.Adoption;
        result[6] = Status.Rejected;
        result[7] = Status.Registered;
        result[8] = Status.Recommended;
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
