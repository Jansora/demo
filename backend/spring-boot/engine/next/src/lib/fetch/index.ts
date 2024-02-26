export declare class ResultDto<T> {
    status?: boolean
    data?: T
    errorCode?: string
    errorDesc?: string
    errorClass?: string
}

export declare class GraphQLResultDto<T> {
    errors: {
        message: string
        extensions: {
            classification
        }
    } []
    data: {
        __data: any
    }
    errorCode: string
    errorDesc: string
    errorClass: string
}

export function result(status: boolean, data?: any, errorCode?: any, errorDesc?: any): ResultDto<any> {
    return {
        errorClass: "", errorCode: errorCode || "", errorDesc: errorDesc || "",
        status,
        data: data || null
    }
}
