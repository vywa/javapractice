namespace java com.youpu.service

include "dto.thrift"

//服务
service PersonService  {

    //用于检测client-server之间是否通讯正常
    string ping(),

    list<dto.Person> getPersonList(1:dto.QueryParam parameter)
}