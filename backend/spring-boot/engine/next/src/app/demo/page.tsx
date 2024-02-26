'use client'

import {redirect} from "next/navigation";
import React, {useEffect, useState} from "react";
import {CodeEditor} from "@jansora/monaco";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";


const meta = {
    functions: [
        {'税前工资': ' salary.base '},
        {'当前工资': '  salary.currentSalary  '},
        {'试用期系数': ' ( 0.8 ) '},
        {'公积金': ' ( 税前工资 * 0.12 ) '},
        {'绩效系数': ' ( 0.70 ) '},
        {'绩效工资': '  ( 税前工资 * ( 1 - 绩效系数) * 0.70 )  '},
        {'加班工资': ' ( 500 * 3 ) '},
    ]
}
export default function Page() {

    const [code, setCode] = useState(JSON.stringify(meta, null, 2));


    const [loadingCodeEditor, setLoadingCodeEditor] = useState(false)

    const dark = GetDarkMode();

    return (
        <>

            <div className="flex p-8">
                <div className="w-1/2">
                    <small className="text-sm font-medium leading-none mb-5 block">元数据编辑</small>
                    {
                        !loadingCodeEditor &&
                        <CodeEditor
                            dark={dark}
                            force={false}
                            id={"code-editor-drools-run"}
                            language={"json"}
                            value={code}
                            onChange={setCode}
                            style={{height:450}}
                        />
                    }

                </div>
                <div className="flex-auto ml-5">
                    <div>
                        <small className="text-sm font-medium leading-none mb-5 block">功能模块渲染</small>

                        <small className="text-sm font-medium leading-none mb-5 block">公式渲染</small>

                        <p className="mt-3 text-lg">Get started by editing{' '}
                            <code className="text-blue-500">pages/index.js</code>
                        </p>
                    </div>
                </div>

            </div>

        </>
    )
}
