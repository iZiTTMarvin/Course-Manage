import request from "@/request";

// 参数接口
export interface UpdateScheduleParams {
    id?: number;
    team?: number;
    time?: number;
    week?: number;
    list?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 修改编号
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.team 班级@
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {number} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateSchedule(params: UpdateScheduleParams): Promise<UpdateScheduleRes> {
    return request.post(`/scheduleEntity/update`, params);
}

// 响应接口
export interface RecoverListScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListSchedule(): Promise<RecoverListScheduleRes> {
    return request.post(`/scheduleEntity/recoverList`);
}

// 响应接口
export interface RecoverScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复数据
 * @param {string} param
 * @returns
 */
export function recoverSchedule(param: string): Promise<RecoverScheduleRes> {
    return request.post(`/scheduleEntity/recover/${param}`);
}
// 响应接口
export interface ListPageScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 列表分页
 * @param {string} page
 * @param {string} pageSize
 * @returns
 */
export function listPageSchedule(page: number, pageSize: number): Promise<ListPageScheduleRes> {
    return request.post(`/scheduleEntity/listPageSchedule?page=${page}&pageSize=${pageSize}`);
}
// 参数接口
export interface InsertScheduleParams {
    id?: number;
    team?: number;
    time?: number;
    week?: number;
    list?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存菜单
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.team 班级@
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {number} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertSchedule(params: InsertScheduleParams): Promise<InsertScheduleRes> {
    return request.post(`/scheduleEntity/insert`, params);
}
// 响应接口
export interface ImportExcelScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 导入数据
 * @param {string} file
 * @returns
 */
export function importExcelSchedule(file: object): Promise<ImportExcelScheduleRes> {
    return request.post(`/scheduleEntity/importExcel`,file);
}


// 参数接口
export interface FindScheduleParams {
    id?: number;
    team?: number;
    time?: number;
    week?: number;
    list?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 条件查询
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.team 班级@
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {number} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findSchedule(params: FindScheduleParams): Promise<FindScheduleRes> {
    return request.post(`/scheduleEntity/findSchedule`, params);
}

// 响应接口
export interface ExportExcelScheduleRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelSchedule(): Promise<ExportExcelScheduleRes> {
    return request.post(`/scheduleEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 暂时删除
 * @param {string} param
 * @returns
 */
export function deleteTempSchedule(param: string): Promise<DeleteTempScheduleRes> {
    return request.post(`/scheduleEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteScheduleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 永久删除
 * @param {string} param
 * @returns
 */
export function deleteSchedule(param: string): Promise<DeleteScheduleRes> {
    return request.post(`/scheduleEntity/delete/${param}`);
}



