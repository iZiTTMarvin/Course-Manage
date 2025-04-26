import request from "@/request";

// 参数接口
export interface UpdateTypeParams {
    id?: number;
    name?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateTypeRes {
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
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateType(params: UpdateTypeParams): Promise<UpdateTypeRes> {
    return request.post(`/typeEntity/update`, params);
}

// 响应接口
export interface RecoverListTypeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListType(): Promise<RecoverListTypeRes> {
    return request.post(`/typeEntity/recoverList`);
}

// 响应接口
export interface RecoverTypeRes {
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
export function recoverType(param: string): Promise<RecoverTypeRes> {
    return request.post(`/typeEntity/recover/${param}`);
}

// 响应接口
export interface ListPageTypeRes {
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
export function listPageType(page: number, pageSize: number): Promise<ListPageTypeRes> {
    return request.post(`/typeEntity/listPageType?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertTypeParams {
    id?: number;
    name?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertTypeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存课程类型
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertType(params: InsertTypeParams): Promise<InsertTypeRes> {
    return request.post(`/typeEntity/insert`, params);
}

// 响应接口
export interface ImportExcelTypeRes {
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
export function importExcelType(file: object): Promise<ImportExcelTypeRes> {
    return request.post(`/typeEntity/importExcel`,file);
}
// 参数接口
export interface FindTypeParams {
    id?: number;
    name?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindTypeRes {
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
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findType(params: FindTypeParams): Promise<FindTypeRes> {
    return request.post(`/typeEntity/findType`, params);
}
// 响应接口
export interface ExportExcelTypeRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelType(): Promise<ExportExcelTypeRes> {
    return request.post(`/typeEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}
// 响应接口
export interface DeleteTempTypeRes {
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
export function deleteTempType(param: string): Promise<DeleteTempTypeRes> {
    return request.post(`/typeEntity/deleteTemp/${param}`);
}
// 响应接口
export interface DeleteTypeRes {
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
export function deleteType(param: string): Promise<DeleteTypeRes> {
    return request.post(`/typeEntity/delete/${param}`);
}
