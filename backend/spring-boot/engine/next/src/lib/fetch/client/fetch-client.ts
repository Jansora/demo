// import {fetchCurrentUser} from "@/lib/utils";

import {toast} from "@jansora/ui/esm/components/ui/use-toast";
import {result, ResultDto} from "@/lib/fetch";
import {useEffect, useState} from "react";

export function clientHeader() {

    return {
        'Content-Type': 'application/json'
    }
}


export function useFetchGraphQLClient(initialData: any,  initialLoading: boolean, dsl, options?: any ){

    const [loading, setLoading] = useState<boolean>(initialLoading)

    //@ts-ignore
    // const [metadata, setMetadata] = useState<ResultDto<any>>({})

    const [data, setData] = useState<any>(initialData)

    useEffect(() => {
        if (loading) {
            fetchClientGraphQL(dsl)
                .then(({data}) => {
                    if (data.status) {
                        setData(data.data)
                    }
                    else {
                        toast({
                            variant: "destructive",
                            title: data.errorCode,
                            description: data.errorDesc,
                        })
                    }
                })
                .catch(reason => {
                    // 捕获网络异常或抛出的错误
                    console.error('网络错误, 请检查网络连接:', reason);
                    toast({
                        variant: "destructive",
                        description: "网络错误, 请检查网络连接",
                    })
                    // throw new Error("网络错误, 请检查网络连接:", error)
                })
                .finally(() => {
                    setLoading(false)
                })

        }

    }, [loading])


    // @ts-ignore
    return [data, loading, setLoading];
}



export function useFetchClient(initialData: any, initialLoading: boolean, url: string, options?: any ){

    const [loading, setLoading] = useState<boolean>(initialLoading)

    const [data, setData] = useState<any>(initialData)

    useEffect(() => {
        if (loading) {
            fetchClient(url, options, setLoading)
                .then(({data}) => {
                    if (data.status) {
                        setData(data.data)
                    }
                    else {
                        toast({
                            variant: "destructive",
                            title: data.errorCode,
                            description: data.errorDesc,
                        })
                    }
                })
                .catch(reason => {
                    // 捕获网络异常或抛出的错误
                    console.error('网络错误, 请检查网络连接:', reason);
                    toast({
                        variant: "destructive",
                        description: "网络错误, 请检查网络连接",
                    })
                    // throw new Error("网络错误, 请检查网络连接:", error)
                })
                // .finally(() => {
                //     setLoading(false)
                // })

        }

    }, [loading])


    // @ts-ignore
    return [data, loading, setLoading];
}


export async function fetchClientGraphQL(dsl, options?: any ) : Promise<{
    data: ResultDto<any>;
    response: Response;
}>  {

    return await fetchClient("/graphql", {
        method: 'POST',
        body: JSON.stringify({query: dsl}),
        headers: clientHeader(),
        ...options || {},
    })
}



export async function fetchClient(url: string, options?: any, setLoading?:any, formBody?: boolean): Promise<{
    data: ResultDto<any>;
    response: Response;
}>  {

    let response: Response;

    setLoading && setLoading(true)
    try {
        // 发送请求

        response = await fetch("/api" + url, !formBody ? {
            headers: clientHeader(),
            ...options ,
        }: { ...(options || {})});

        setLoading && setLoading(false)

        // 检查响应状态码
        if (response.ok) {
            // 检查响应的内容类型
            const contentType = response.headers.get("content-type");

            // 根据内容类型解析响应数据
            let data : ResultDto<any>;
            if (contentType && contentType.includes("application/json")) {
                // 解析 JSON 数据
                data = await response.json();
            } else if (contentType && contentType.includes("text")) {
                // 解析纯文本数据
                // @ts-ignore
                data = await response.text();
            } else if (contentType && contentType.includes("form")) {
                // 解析表单数据
                // @ts-ignore
                data = await response.formData();
            } else if (contentType && contentType.includes("image")) {
                // 解析二进制数据（如图片）
                // @ts-ignore
                data = await response.blob();
            } else {
                // 对于其他类型或未知类型，使用 arrayBuffer
                // @ts-ignore
                data = await response.arrayBuffer();
            }

            // 这里可以处理数据
            // console.log("fetchClient data", data);

            if (data.status == false) {
                toast({
                    variant: "destructive",
                    description: data.errorDesc,
                })
            }
            return {response, data};
        }

        const data = await response.json();

        if (data.status == false) {
            toast({
                variant: "destructive",
                description: data.errorDesc,
            })
        }
        return {response, data};
        //
        // else if (response.status > 399) {
        //     toast({
        //         variant: "destructive",
        //         description: `连接异常: ${response.status} ${response.statusText} `,
        //     })
        // }
        // else if (response.status === 500) {
        //
        //     let data = await response.json();
        //
        //     if (data.status == false) {
        //         toast({
        //             variant: "destructive",
        //             description: data.errorDesc,
        //         })
        //     }
        // }
        // else if (response.status >= 500) {
        //
        //
        //     // 如果状态码不在 200-299 范围内，抛出错误
        //     toast({
        //         variant: "destructive",
        //         description: `服务异常: ${response.status} ${response.statusText} `,
        //     })
        // }

    } catch (error) {
        // 捕获网络异常或抛出的错误
        console.error('网络错误, 请检查网络连接:', error);
        toast({
            variant: "destructive",
            description: "网络错误, 请检查网络连接",
        })
        // throw new Error("网络错误, 请检查网络连接:", error)
    }

    // @ts-ignore
    return {response, data: result(false, {})};
}

export const fetchClientLogout = async ()  => {
    const {response, data} = await fetchClient("/auth/logout", {
        method: 'POST',
    })
    return data
}
export const fetchClientUpload = async (file: File)  => {
    const formData = new FormData();
    formData.append('file', file);
    const {response, data} = await fetchClient("/utils/upload", {
        method: 'POST',
        body: formData,
        // headers: {
        //     'Content-Type': 'multipart/form-data;',
        // },
        // headers: null
    }, null, true)

    return data.status ? data.data : {}
}

export const fetchClientUploads = async (files: File[])  => {
    const formData = new FormData();
    files.forEach(function (file) {
        formData.append('file', file);
    });
    const {response, data} = await fetchClient("/utils/uploads", {
        method: 'POST',
        body: formData,
    }, null, true)

    console.log("bbc", data.data)
    return data.status ? data.data : []
}

