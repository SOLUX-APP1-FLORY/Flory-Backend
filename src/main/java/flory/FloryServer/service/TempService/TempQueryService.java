package flory.FloryServer.service.TempService;

//GET 요청에 대한 비즈니스 로직
public interface TempQueryService {

    void CheckFlag(Integer flag);
    //Non-static method 'CheckFlag(java.lang.Integer)' cannot be referenced from a static context
    // -> Make static
}
