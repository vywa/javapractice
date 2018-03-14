namespace java com.youpu.dto

struct Person {
    1:i16 age = 0,
    2:string name,
    3:bool sex,
    4:double salary,
    5:i8 childrenCount
}

//查询参数

struct QueryParam {
    1:i16 ageStart,
    2:i16 ageEnd
}