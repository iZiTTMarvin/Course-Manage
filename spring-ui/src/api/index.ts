import * as login from "@/api/login"
import * as user from "@/api/user"
import * as role from "@/api/role"
import * as menu from "@/api/menu"
import * as type from "@/api/type"
import * as list from "@/api/list"
import * as table from "@/api/table"
import * as time from "@/api/time"
import * as academic from "@/api/academic"
import * as classroom from "@/api/classroom"
import * as profess from "@/api/profess"
import * as team from "@/api/team"
import * as schedule from "@/api/schedule"
import * as grade from "@/api/grade"
import * as others from "@/api/others"

const api = {
    login, user, role, menu,
    type, list, table, time,
    academic, classroom, profess, team,
    schedule, grade, others
}

export default api;