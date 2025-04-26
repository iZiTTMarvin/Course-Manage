import request from "@/request";

// 响应接口
export interface UpdateRmRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 暂时删除
 * @param {string} role
 * @param {string} menu
 * @returns
 */
export function updateRm(role: number, menu: number): Promise<UpdateRmRes> {
    return request.post(`/rmEntity/updateRm?role=${role}&menu=${menu}`);
}

// 响应接口
export interface RecoverRmRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * 恢复数据
 * @param {string} role
 * @param {string} menu
 * @returns
 */
export function recoverRm(role: number, menu: number): Promise<RecoverRmRes> {
    return request.post(`/rmEntity/recoverRm?role=${role}&menu=${menu}`);
}

