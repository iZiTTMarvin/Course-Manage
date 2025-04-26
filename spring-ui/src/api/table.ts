import request from "@/request";

// 参数接口
export interface UpdateTableParams {
    id?: number;
    time?: number;
    week?: number;
    list?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateTableRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 修改编号
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {string} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateTable(params: UpdateTableParams): Promise<UpdateTableRes> {
    return request.post(`/tableEntity/update`, params);
}

// 响应接口
export interface RecoverListTableRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListTable(): Promise<RecoverListTableRes> {
    return request.post(`/tableEntity/recoverList`);
}

// 响应接口
export interface RecoverTableRes {
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
export function recoverTable(param: string): Promise<RecoverTableRes> {
    return request.post(`/tableEntity/recover/${param}`);
}

// 响应接口
export interface ListPageTableRes {
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
export function listPageTable(page: number, pageSize: number): Promise<ListPageTableRes> {
    return request.post(`/tableEntity/listPageTable?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertTableParams {
    id?: number;
    time?: number;
    week?: number;
    list?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertTableRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存菜单
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {string} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertTable(params: InsertTableParams): Promise<InsertTableRes> {
    return request.post(`/tableEntity/insert`, params);
}

// 响应接口
export interface ImportExcelTableRes {
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
export function importExcelTable(file: object): Promise<ImportExcelTableRes> {
    return request.post(`/tableEntity/importExcel`,file);
}

// 参数接口
export interface FindTableParams {
    id?: number;
    time?: number;
    week?: number;
    list?: string;
    classroom?: string;
    teacher?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}


// 响应接口
export interface FindTableRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 条件查询
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.time 时间@
 * @param {number} params.week 星期（1~7)
 * @param {string} params.list 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findTable(params: FindTableParams): Promise<FindTableRes> {
    return request.post(`/tableEntity/findTable`, params);
}

// 响应接口
export interface ExportExcelTableRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelTable(): Promise<ExportExcelTableRes> {
    return request.post(`/tableEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempTableRes {
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
export function deleteTempTable(param: string): Promise<DeleteTempTableRes> {
    return request.post(`/tableEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteTableRes {
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
export function deleteTable(param: string): Promise<DeleteTableRes> {
    return request.post(`/tableEntity/delete/${param}`);
}
