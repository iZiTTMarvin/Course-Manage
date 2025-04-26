import request from "@/request";

// 参数接口
export interface UpdateMenuParams {
    id?: number;
    name?: string;
    icon?: string;
    keypath?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateMenuRes {
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
 * @param {string} params.icon 图标
 * @param {string} params.keypath 标识符
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateMenu(params: UpdateMenuParams): Promise<UpdateMenuRes> {
    return request.post(`/menuEntity/update`, params);
}

// 响应接口
export interface RecoverListMenuRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListMenu(): Promise<RecoverListMenuRes> {
    return request.post(`/menuEntity/recoverList`);
}

// 响应接口
export interface RecoverMenuRes {
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
export function recoverMenu(param: string): Promise<RecoverMenuRes> {
    return request.post(`/menuEntity/recover/${param}`);
}

// 响应接口
export interface ListPageMenuRes {
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
export function listPageMenu(page: number, pageSize: number): Promise<ListPageMenuRes> {
    return request.post(`/menuEntity/listPageMenu?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertMenuParams {
    id?: number;
    name?: string;
    icon?: string;
    keypath?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertMenuRes {
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
 * @param {string} params.icon 图标
 * @param {string} params.keypath 标识符
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertMenu(params: InsertMenuParams): Promise<InsertMenuRes> {
    return request.post(`/menuEntity/insert`, params);
}

// 响应接口
export interface ImportExcelMenuRes {
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
export function importExcelMenu(file: object): Promise<ImportExcelMenuRes> {
    return request.post(`/menuEntity/importExcel`,file);
}

// 参数接口
export interface FindMenuParams {
    id?: number;
    name?: string;
    icon?: string;
    keypath?: string;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindMenuRes {
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
 * @param {string} params.icon 图标
 * @param {string} params.keypath 标识符
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findMenu(params: FindMenuParams): Promise<FindMenuRes> {
    return request.post(`/menuEntity/findMenu`, params);
}

// 响应接口
export interface FindByNameMenuRes {
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
export function findByNameMenu(param: string): Promise<FindByNameMenuRes> {
    return request.post(`/menuEntity/findByNameMenu/${param}`);
}

// 响应接口
export interface FindByIDMenuRes {
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
export function findByIDMenu(param: string): Promise<FindByIDMenuRes> {
    return request.post(`/menuEntity/findByIDMenu/${param}`);
}

// 响应接口
export interface ExportExcelMenuRes {}

/**
 * 导出数据
 * @returns
 */
export function exportExcelMenu(): Promise<ExportExcelMenuRes> {
    return request.post(`/menuEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}
// 响应接口
export interface DeleteTempMenuRes {
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
export function deleteTempMenu(param: string): Promise<DeleteTempMenuRes> {
    return request.post(`/menuEntity/deleteTemp/${param}`);
}
// 响应接口
export interface DeleteMenuRes {
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
export function deleteMenu(param: string): Promise<DeleteMenuRes> {
    return request.post(`/menuEntity/delete/${param}`);
}
