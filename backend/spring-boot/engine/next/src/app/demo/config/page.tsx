'use client'

import React, {useEffect, useState} from "react";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";
import {CodeEditor} from "@jansora/monaco";
import {Dataset, DatasetSelector} from "@/components/layout/DatasetSelector";
import {droolsRuleDatasets} from './datasets'
import {Button} from "@jansora/ui/esm/components/ui/button";
import {SaveDroolsRule} from "@/lib/fetch/client/fetch";
import {Loading} from "@jansora/ui/esm/components/enhanced/ui/Loading";
import {Save} from "lucide-react";

export default function Page() {
    const [code, setCode] = useState(`
    `)

    const dark = GetDarkMode();
    // const books = initBooks(config.categories[0])

    const [dataset, setDataset] = useState<Dataset>(droolsRuleDatasets[0])

    const [_, saveLoading, setSaveLoading] = SaveDroolsRule(        code);

    const [loadingCodeEditor, setLoadingCodeEditor] = useState(false)
    useEffect(() => {
        setCode(dataset.payload)
        setLoadingCodeEditor(true)
        setTimeout(() => {
            setLoadingCodeEditor(false)
        }, 0)
    }, [dataset])
    return (
        <>
            <div className="mb-5">
                <span className="text-muted-foreground mr-2"> 预置规则集 </span>
                <DatasetSelector datasets={droolsRuleDatasets} dataset={dataset} setDataset={setDataset} />
                <div className="float-right">

                    <Button
                        variant="outline"
                        // loading={compilerLoading}
                        // @ts-ignore
                        disabled={saveLoading}
                        onClick={() => { // @ts-ignore
                            setSaveLoading(true);}}
                    >
                        <Loading loading={!!saveLoading} className="w-4 h-4 mr-2">
                            <Save  className={"w-4 h-4 mr-2"} />
                        </Loading>
                        保存
                    </Button>
                </div>
            </div>
            {
                !loadingCodeEditor &&
                <CodeEditor
                    dark={dark}
                    force={false}
                    id={"code-editor-drools-config"}
                    language={"java"}
                    value={code}
                    onChange={setCode}
                    style={{height:450}}
                />
            }

        </>
    )
}
