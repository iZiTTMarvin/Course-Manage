import request from "@/request";


// 参数接口
export interface UpdateListParams {
    id?: number;
    name?: string;
    teacher?: number;
    grade?: string;
    type?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateListRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 修改数据
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {number} params.teacher 老师@
 * @param {string} params.grade 学分
 * @param {number} params.type 类型@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateList(params: UpdateListParams): Promise<UpdateListRes> {
    return request.post(`/listEntity/update`, params);
}

// 响应接口
export interface RecoverListListRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListList(): Promise<RecoverListListRes> {
    return request.post(`/listEntity/recoverList`);
}

// 响应接口
export interface RecoverListRes {
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
export function recoverList(param: string): Promise<RecoverListRes> {
    return request.post(`/listEntity/recover/${param}`);
}

// 响应接口
export interface ListPageListRes {
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
export function listPageList(page: number, pageSize: number): Promise<ListPageListRes> {
    return request.post(`/listEntity/listPageList?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertListParams {
    id?: number;
    name?: string;
    teacher?: number;
    grade?: string;
    type?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertListRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存课程
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {number} params.teacher 老师@
 * @param {string} params.grade 学分
 * @param {number} params.type 类型@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertList(params: InsertListParams): Promise<InsertListRes> {
    return request.post(`/listEntity/insert`, params);
}

// 响应接口
export interface ImportExcelListRes {
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
export function importExcelList(file: object): Promise<ImportExcelListRes> {
    return request.post(`/listEntity/importExcel`,file);
}

// 参数接口
export interface FindListParams {
    id?: number;
    name?: string;
    grade?: string;
    type?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindListRes {
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
 * @param {number} params.teacher 老师@
 * @param {string} params.grade 学分
 * @param {number} params.type 类型@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findList(params: FindListParams): Promise<FindListRes> {
    return request.post(`/listEntity/findList`, params);
}

// 响应接口
export interface ExportExcelListRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelList(): Promise<ExportExcelListRes> {
    return request.post(`/listEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempListRes {
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
export function deleteTempList(param: string): Promise<DeleteTempListRes> {
    return request.post(`/listEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteListRes {
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
export function deleteList(param: string): Promise<DeleteListRes> {
    return request.post(`/listEntity/delete/${param}`);
}

