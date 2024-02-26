import {useEffect, useState} from 'react';
// import {client} from "@jansora/material/es/request";
import {ResultDto} from "@/lib/fetch";
import {fetchClient} from "@/lib/fetch/client/fetch-client";

import {toast} from "sonner"
// Required to let webpack 4 know it needs to copy the wasm file to our assets
// import sqlWasm from "!!file-loader?name=sql-wasm-[contenthash].wasm!sql.js/dist/sql-wasm.wasm";
// import sqlWasm from "sql.js/dist/sql-wasm.js";

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 2020/12/05 14:25:13
 */

export const SaveDroolsRule = (payload) => {

    const [result, setResult] = useState<ResultDto<any>>({status: undefined, errorDesc: '', data: ''});
    const [loading, setLoading] = useState(false);
    useEffect(()=> {
        if(loading) {
            fetchClient("/modules/drools/rule/pom/reload", {
                method: 'PUT',
                body: JSON.stringify({payload})
            })
                .then(response =>  {
                    if (response.data.status) {
                        setResult(response.data)
                        toast.success(  <span> 保存成功 </span>,
                            {
                                position: "top-center",
                                //@ts-ignore
                                description: <span className="whitespace-normal w-[350px] inline-block"></span>,
                            })
                    }
                }).catch( e => {
                setResult({status: false, errorDesc: '', data:(e.data && e.data.errorDesc) ? e.data.errorDesc : "未知异常" })
            }).finally(()=> {
                setLoading(false)
            })
        }
        // eslint-disable-next-line
    }, [loading, payload]);


    return [result, loading, setLoading];


};



export const RunDroolsRule = (payload, successCallback) => {

    const [result, setResult] = useState<ResultDto<any>>({status: undefined, errorDesc: '', data: ''});
    const [loading, setLoading] = useState(false);
    useEffect(()=> {
        if(loading) {
            fetchClient("/modules/drools/rule/pom/run", {
                method: 'POST',
                body: payload
            })
                .then(response =>  {
                    if (response.data.status) {
                        setResult(response.data)
                        successCallback && successCallback(response.data)
                        toast.success(  <span> 运行成功 </span>,
                            {
                                position: "top-center",
                            //@ts-ignore
                            description: <span className="whitespace-normal w-[350px] inline-block"></span>,
                        })
                    }
                }).catch( e => {
                setResult({status: false, errorDesc: '', data:(e.data && e.data.errorDesc) ? e.data.errorDesc : "未知异常" })
            }).finally(()=> {
                setLoading(false)
            })
        }
        // eslint-disable-next-line
    }, [loading, payload]);


    return [result, loading, setLoading];


};



export const SaveLiteflowRule = (payload) => {

    const [result, setResult] = useState<ResultDto<any>>({status: undefined, errorDesc: '', data: ''});
    const [loading, setLoading] = useState(false);
    useEffect(()=> {
        if(loading) {
            fetchClient("/modules/liteflow/rule/pom/reload", {
                method: 'PUT',
                body: JSON.stringify({payload})
            })
                .then(response =>  {
                    if (response.data.status) {
                        setResult(response.data)
                        toast.success(  <span> 保存成功 </span>,
                            {
                                position: "top-center",
                                //@ts-ignore
                                description: <span className="whitespace-normal w-[350px] inline-block"></span>,
                            })
                    }
                }).catch( e => {
                setResult({status: false, errorDesc: '', data:(e.data && e.data.errorDesc) ? e.data.errorDesc : "未知异常" })
            }).finally(()=> {
                setLoading(false)
            })
        }
        // eslint-disable-next-line
    }, [loading, payload]);


    return [result, loading, setLoading];


};



export const RunLiteflowRule = (payload, successCallback) => {

    const [result, setResult] = useState<ResultDto<any>>({status: undefined, errorDesc: '', data: ''});
    const [loading, setLoading] = useState(false);
    useEffect(()=> {
        if(loading) {
            fetchClient("/modules/liteflow/rule/pom/run", {
                method: 'POST',
                body: payload
            })
                .then(response =>  {
                    if (response.data.status) {
                        setResult(response.data)
                        successCallback && successCallback(response.data)
                        toast.success(  <span> 运行成功 </span>,
                            {
                                position: "top-center",
                                //@ts-ignore
                                description: <span className="whitespace-normal w-[350px] inline-block"></span>,
                            })
                    }
                }).catch( e => {
                setResult({status: false, errorDesc: '', data:(e.data && e.data.errorDesc) ? e.data.errorDesc : "未知异常" })
            }).finally(()=> {
                setLoading(false)
            })
        }
        // eslint-disable-next-line
    }, [loading, payload]);


    return [result, loading, setLoading];


};
