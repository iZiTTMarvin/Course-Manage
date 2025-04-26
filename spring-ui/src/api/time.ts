import request from "@/request";

// 参数接口
export interface UpdateTimeParams {
    id?: number;
    name?: string;
    start?: string;
    end?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateTimeRes {
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
 * @param {string} params.start 开始时间
 * @param {string} params.end 结束时间
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateTime(params: UpdateTimeParams): Promise<UpdateTimeRes> {
    return request.post(`/timeEntity/update`, params);
}

// 响应接口
export interface RecoverListTimeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListTime(): Promise<RecoverListTimeRes> {
    return request.post(`/timeEntity/recoverList`);
}

// 响应接口
export interface RecoverTimeRes {
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
export function recoverTime(param: string): Promise<RecoverTimeRes> {
    return request.post(`/timeEntity/recover/${param}`);
}

// 响应接口
export interface ListPageTimeRes {
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
export function listPageTime(page: number, pageSize: number): Promise<ListPageTimeRes> {
    return request.post(`/timeEntity/listPageTime?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertTimeParams {
    id?: number;
    name?: string;
    start?: string;
    end?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertTimeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存数据
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {string} params.name 名称
 * @param {string} params.start 开始时间
 * @param {string} params.end 结束时间
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertTime(params: InsertTimeParams): Promise<InsertTimeRes> {
    return request.post(`/timeEntity/insert`, params);
}

// 响应接口
export interface ImportExcelTimeRes {
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
export function importExcelTime(file: object): Promise<ImportExcelTimeRes> {
    return request.post(`/timeEntity/importExcel`,file);
}

// 参数接口
export interface FindTimeParams {
    id?: number;
    name?: string;
    start?: string;
    end?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindTimeRes {
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
 * @param {string} params.start 开始时间
 * @param {string} params.end 结束时间
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findTime(params: FindTimeParams): Promise<FindTimeRes> {
    return request.post(`/timeEntity/findTime`, params);
}

// 响应接口
export interface ExportExcelTimeRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelTime(): Promise<ExportExcelTimeRes> {
    return request.post(`/timeEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempTimeRes {
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
export function deleteTempTime(param: string): Promise<DeleteTempTimeRes> {
    return request.post(`/timeEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteTimeRes {
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
export function deleteTime(param: string): Promise<DeleteTimeRes> {
    return request.post(`/timeEntity/delete/${param}`);
}

