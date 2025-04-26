import request from "@/request";

// 参数接口
export interface UpdateRoleParams {
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
export interface UpdateRoleRes {
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
export function updateRole(params: UpdateRoleParams): Promise<UpdateRoleRes> {
    return request.post(`/roleEntity/update`, params);
}
// 响应接口
export interface RecoverListRoleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListRole(): Promise<RecoverListRoleRes> {
    return request.post(`/roleEntity/recoverList`);
}

// 响应接口
export interface RecoverRoleRes {
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
export function recoverRole(param: string): Promise<RecoverRoleRes> {
    return request.post(`/roleEntity/recover/${param}`);
}
// 响应接口
export interface ListPageRoleRes {
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
export function listPageRole(page: number, pageSize: number): Promise<ListPageRoleRes> {
    return request.post(`/roleEntity/listPageRole?page=${page}&pageSize=${pageSize}`);
}
// 参数接口
export interface InsertRoleParams {
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
export interface InsertRoleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存用户
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
export function insertRole(params: InsertRoleParams): Promise<InsertRoleRes> {
    return request.post(`/roleEntity/insert`, params);
}

// 响应接口
export interface ImportExcelRoleRes {
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
export function importExcelRole(file: object): Promise<ImportExcelRoleRes> {
    return request.post(`/roleEntity/importExcel`,file);
}
// 参数接口
export interface FindRoleParams {
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
export interface FindRoleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 查询条件
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
export function findRole(params: FindRoleParams): Promise<FindRoleRes> {
    return request.post(`/roleEntity/findRole`, params);
}
// 响应接口
export interface FindByNameRoleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 查询名称
 * @param {string} param
 * @returns
 */
export function findByNameRole(param: string): Promise<FindByNameRoleRes> {
    return request.post(`/roleEntity/findByNameRole/${param}`);
}
// 响应接口
export interface FindByIDRoleRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 查询编号
 * @param {string} param
 * @returns
 */
export function findByIDRole(param: string): Promise<FindByIDRoleRes> {
    return request.post(`/roleEntity/findByIDRole/${param}`);
}
// 响应接口
export interface ExportExcelRoleRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelRole(): Promise<ExportExcelRoleRes> {
    return request.post(`/roleEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempRoleRes {
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
export function deleteTempRole(param: string): Promise<DeleteTempRoleRes> {
    return request.post(`/roleEntity/deleteTemp/${param}`);
}
// 响应接口
export interface DeleteRoleRes {
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
export function deleteRole(param: string): Promise<DeleteRoleRes> {
    return request.post(`/roleEntity/delete/${param}`);
}





