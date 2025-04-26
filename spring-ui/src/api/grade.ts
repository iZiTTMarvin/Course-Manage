import request from "@/request";


// 参数接口
export interface UpdateGradeParams {
    id?: number;
    user?: number;
    list?: number;
    score?: number;
    grade?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface UpdateGradeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 修改编号
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.user 用户@
 * @param {number} params.list 课程@
 * @param {number} params.score 分数
 * @param {number} params.grade 学分
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function updateGrade(params: UpdateGradeParams): Promise<UpdateGradeRes> {
    return request.post(`/gradeEntity/update`, params);
}

// 响应接口
export interface RecoverListGradeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复列表
 * @returns
 */
export function recoverListGrade(): Promise<RecoverListGradeRes> {
    return request.post(`/gradeEntity/recoverList`);
}

// 响应接口
export interface RecoverGradeRes {
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
export function recoverGrade(param: string): Promise<RecoverGradeRes> {
    return request.post(`/gradeEntity/recover/${param}`);
}

// 响应接口
export interface ListPageGradeRes {
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
export function listPageGrade(page: number, pageSize: number): Promise<ListPageGradeRes> {
    return request.post(`/gradeEntity/listPageGrade?page=${page}&pageSize=${pageSize}`);
}

// 参数接口
export interface InsertGradeParams {
    id?: number;
    user?: number;
    list?: number;
    score?: number;
    grade?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface InsertGradeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 保存数据
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.user 用户@
 * @param {number} params.list 课程@
 * @param {number} params.score 分数
 * @param {number} params.grade 学分
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function insertGrade(params: InsertGradeParams): Promise<InsertGradeRes> {
    return request.post(`/gradeEntity/insert`, params);
}

// 响应接口
export interface ImportExcelGradeRes {
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
export function importExcelGrade(file: object): Promise<ImportExcelGradeRes> {
    return request.post(`/gradeEntity/importExcel`,file);
}

// 参数接口
export interface FindGradeParams {
    id?: number;
    user?: number;
    list?: number;
    score?: number;
    grade?: number;
    createBy?: string;
    createTime?: Record<string, unknown>;
    updateBy?: string;
    updateTime?: Record<string, unknown>;
    undock?: number;
    state?: number;
    remark?: string;
}

// 响应接口
export interface FindGradeRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 条件查询
 * @param {object} params $!{table.comment}
 * @param {number} params.id 编号
 * @param {number} params.user 用户@
 * @param {number} params.list 课程@
 * @param {number} params.score 分数
 * @param {number} params.grade 学分
 * @param {string} params.createBy 创建者
 * @param {object} params.createTime 创建时间
 * @param {string} params.updateBy 更新者
 * @param {object} params.updateTime 更新时间
 * @param {number} params.undock 伪删除
 * @param {number} params.state 状态
 * @param {string} params.remark 备注
 * @returns
 */
export function findGrade(params: FindGradeParams): Promise<FindGradeRes> {
    return request.post(`/gradeEntity/findGrade`, params);
}

// 响应接口
export interface ExportExcelGradeRes {
}

/**
 * 导出数据
 * @returns
 */
export function exportExcelGrade(): Promise<ExportExcelGradeRes> {
    return request.post(`/gradeEntity/exportExcel`, {}, {
        responseType: "blob"
    });
}

// 响应接口
export interface DeleteTempGradeRes {
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
export function deleteTempGrade(param: string): Promise<DeleteTempGradeRes> {
    return request.post(`/gradeEntity/deleteTemp/${param}`);
}

// 响应接口
export interface DeleteGradeRes {
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
export function deleteGrade(param: string): Promise<DeleteGradeRes> {
    return request.post(`/gradeEntity/delete/${param}`);
}
