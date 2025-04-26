import request from "@/request";


// 参数接口
export interface UpdateProfessParams {
    id?: number;
    name?: string;
    tableList?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateProfessRes {
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
 * @param {string} params.tableList 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateProfess(params: UpdateProfessParams): Promise<UpdateProfessRes> {
    return request.post(`/professEntity/update`, params);
}

// 响应接口
export interface RecoverListProfessRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListProfess(): Promise<RecoverListProfessRes> {
    return request.post(`/professEntity/recoverList`);
}

// 响应接口
export interface RecoverProfessRes {
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
export function recoverProfess(param: string): Promise<RecoverProfessRes> {
    return request.post(`/professEntity/recover/${param}`);
}

// 响应接口
export interface ListPageProfessRes {
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
export function listPageProfess(page: number, pageSize: number): Promise<ListPageProfessRes> {
    return request.post(`/professEntity/listPageProfess?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertProfessParams {
    id?: number;
    name?: string;
    tableList?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertProfessRes {
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
 * @param {string} params.tableList 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertProfess(params: InsertProfessParams): Promise<InsertProfessRes> {
    return request.post(`/professEntity/insert`, params);
}

// 响应接口
export interface ImportExcelProfessRes {
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
export function importExcelProfess(file: object): Promise<ImportExcelProfessRes> {
    return request.post(`/professEntity/importExcel`,file);
}

// 参数接口
export interface FindProfessParams {
    id?: number;
    name?: string;
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
export interface FindProfessRes {
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
 * @param {string} params.tableList 课程@
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findProfess(params: FindProfessParams): Promise<FindProfessRes> {
    return request.post(`/professEntity/findProfess`, params);
}

// 响应接口
export interface ExportExcelProfessRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelProfess(): Promise<ExportExcelProfessRes> {
    return request.post(`/professEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempProfessRes {
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
export function deleteTempProfess(param: string): Promise<DeleteTempProfessRes> {
    return request.post(`/professEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteProfessRes {
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
export function deleteProfess(param: string): Promise<DeleteProfessRes> {
    return request.post(`/professEntity/delete/${param}`);
}

