import request from "@/request";

// 响应接口
export interface GenerateImageCodeRes {}

/**
 * 生成验证码
 * @returns
 */
export function generateImageCode(): Promise<GenerateImageCodeRes> {
    return request.get(`/generateImageCode`, {
        responseType: "arraybuffer",
    });
}

// 响应接口
export interface LoginRes {
    message: string;
    success: boolean;
    code: number;
    data: Record<string, unknown>;
}

/**
 * login
 * @param {string} acc
 * @param {string} pwd
 * @param {string} code
 * @returns
 */
export function login(acc: string, pwd: string, code: string): Promise<LoginRes> {
    return request.post(`/login?acc=${acc}&pwd=${pwd}&code=${code}`);
}
