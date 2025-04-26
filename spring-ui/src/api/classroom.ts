import request from "@/request";


// 参数接口
export interface UpdateClassroomParams {
    id?: number;
    name?: string;
    room?: string;
    site?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateClassroomRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 修改编号
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {string} params.room 教室
 * @param {number} params.site 座位
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateClassroom(params: UpdateClassroomParams): Promise<UpdateClassroomRes> {
    return request.post(`/classroomEntity/update`, params);
}
// 响应接口
export interface RecoverListClassroomRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListClassroom(): Promise<RecoverListClassroomRes> {
    return request.post(`/classroomEntity/recoverList`);
}
// 响应接口
export interface RecoverClassroomRes {
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
export function recoverClassroom(param: string): Promise<RecoverClassroomRes> {
    return request.post(`/classroomEntity/recover/${param}`);
}
// 响应接口
export interface ListPageClassroomRes {
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
export function listPageClassroom(page: number, pageSize: number): Promise<ListPageClassroomRes> {
    return request.post(`/classroomEntity/listPageClassroom?page=${page}&pageSize=${pageSize}`);
}
// 参数接口
export interface InsertClassroomParams {
    id?: number;
    name?: string;
    room?: string;
    site?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertClassroomRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存菜单
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {string} params.room 教室
 * @param {number} params.site 座位
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertClassroom(params: InsertClassroomParams): Promise<InsertClassroomRes> {
    return request.post(`/classroomEntity/insert`, params);
}
// 响应接口
export interface ImportExcelClassroomRes {
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
export function importExcelClassroom(file: object): Promise<ImportExcelClassroomRes> {
    return request.post(`/classroomEntity/importExcel`,file);
}
// 参数接口
export interface FindClassroomParams {
    id?: number;
    name?: string;
    room?: string;
    site?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindClassroomRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 条件查询
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {string} params.room 教室
 * @param {number} params.site 座位
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findClassroom(params: FindClassroomParams): Promise<FindClassroomRes> {
    return request.post(`/classroomEntity/findClassroom`, params);
}
// 响应接口
export interface ExportExcelClassroomRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelClassroom(): Promise<ExportExcelClassroomRes> {
    return request.post(`/classroomEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}
// 响应接口
export interface DeleteTempClassroomRes {
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
export function deleteTempClassroom(param: string): Promise<DeleteTempClassroomRes> {
    return request.post(`/classroomEntity/deleteTemp/${param}`);
}
// 响应接口
export interface DeleteClassroomRes {
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
export function deleteClassroom(param: string): Promise<DeleteClassroomRes> {
    return request.post(`/classroomEntity/delete/${param}`);
}
