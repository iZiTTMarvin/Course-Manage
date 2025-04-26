import request from "@/request";


// 参数接口
export interface UpdateTeamParams {
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
export interface UpdateTeamRes {
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
export function updateTeam(params: UpdateTeamParams): Promise<UpdateTeamRes> {
    return request.post(`/teamEntity/update`, params);
}

// 响应接口
export interface RecoverListTeamRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListTeam(): Promise<RecoverListTeamRes> {
    return request.post(`/teamEntity/recoverList`);
}

// 响应接口
export interface RecoverTeamRes {
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
export function recoverTeam(param: string): Promise<RecoverTeamRes> {
    return request.post(`/teamEntity/recover/${param}`);
}

// 响应接口
export interface ListPageTeamRes {
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
export function listPageTeam(page: number, pageSize: number): Promise<ListPageTeamRes> {
    return request.post(`/teamEntity/listPageTeam?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertTeamParams {
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
export interface InsertTeamRes {
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
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertTeam(params: InsertTeamParams): Promise<InsertTeamRes> {
    return request.post(`/teamEntity/insert`, params);
}

// 响应接口
export interface ImportExcelTeamRes {
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
export function importExcelTeam(file: object): Promise<ImportExcelTeamRes> {
    return request.post(`/teamEntity/importExcel`,file);
}

// 参数接口
export interface FindTeamParams {
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
export interface FindTeamRes {
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
export function findTeam(params: FindTeamParams): Promise<FindTeamRes> {
    return request.post(`/teamEntity/findTeam`, params);
}

// 响应接口
export interface ExportExcelTeamRes {
}

/**
 * 导出数据
 * @returns
 */
export function exportExcelTeam(): Promise<ExportExcelTeamRes> {
    return request.post(`/teamEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempTeamRes {
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
export function deleteTempTeam(param: string): Promise<DeleteTempTeamRes> {
    return request.post(`/teamEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteTeamRes {
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
export function deleteTeam(param: string): Promise<DeleteTeamRes> {
    return request.post(`/teamEntity/delete/${param}`);
}
