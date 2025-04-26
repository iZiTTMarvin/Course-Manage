import request from "@/request";

// 参数接口
export interface UpdateAcademicParams {
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
export interface UpdateAcademicRes {
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
export function updateAcademic(params: UpdateAcademicParams): Promise<UpdateAcademicRes> {
    return request.post(`/academicEntity/update`, params);
}

// 响应接口
export interface RecoverListAcademicRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListAcademic(): Promise<RecoverListAcademicRes> {
    return request.post(`/academicEntity/recoverList`);
}

// 响应接口
export interface RecoverAcademicRes {
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
export function recoverAcademic(param: string): Promise<RecoverAcademicRes> {
    return request.post(`/academicEntity/recover/${param}`);
}

// 响应接口
export interface ListPageAcademicRes {
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
export function listPageAcademic(page: number, pageSize: number): Promise<ListPageAcademicRes> {
    return request.post(`/academicEntity/listPageAcademic?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertAcademicParams {
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
export interface InsertAcademicRes {
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
export function insertAcademic(params: InsertAcademicParams): Promise<InsertAcademicRes> {
    return request.post(`/academicEntity/insert`, params);
}

// 响应接口
export interface ImportExcelAcademicRes {
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
export function importExcelAcademic(file: object): Promise<ImportExcelAcademicRes> {
    return request.post(`/academicEntity/importExcel`,file);
}

// 参数接口
export interface FindAcademicParams {
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
export interface FindAcademicRes {
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
export function findAcademic(params: FindAcademicParams): Promise<FindAcademicRes> {
    return request.post(`/academicEntity/findAcademic`, params);
}

// 响应接口
export interface ExportExcelAcademicRes {
}

/**
 * 导出数据
 * @returns
 */
export function exportExcelAcademic(): Promise<ExportExcelAcademicRes> {
    return request.post(`/academicEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempAcademicRes {
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
export function deleteTempAcademic(param: string): Promise<DeleteTempAcademicRes> {
    return request.post(`/academicEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteAcademicRes {
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
export function deleteAcademic(param: string): Promise<DeleteAcademicRes> {
    return request.post(`/academicEntity/delete/${param}`);
}
