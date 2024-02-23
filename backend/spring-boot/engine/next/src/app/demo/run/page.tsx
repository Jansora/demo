'use client'

import React, {useEffect, useState} from "react";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";
import {CodeEditor} from "@jansora/monaco";
import {Dataset, DatasetSelector} from "@/components/layout/DatasetSelector";
import {droolsDataDatasets} from './datasets'
import {Button} from "@jansora/ui/esm/components/ui/button";
import {RunDroolsRule} from "@/lib/fetch/client/fetch";
import {Loading} from "@jansora/ui/esm/components/enhanced/ui/Loading";
import {TerminalIcon} from "lucide-react";
import {ByteViewer} from "@jansora/bytemd/esm/Viewer";
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger
} from "@jansora/ui/esm/components/ui/dialog";

export default function Page() {
    const [code, setCode] = useState(`
    `)

    const dark = GetDarkMode();
    // const books = initBooks(config.categories[0])
    const [open, setOpen] = React.useState(false)

    const [dataset, setDataset] = useState<Dataset>(droolsDataDatasets[0])

    const [result, saveLoading, setSaveLoading] = RunDroolsRule(code, (data) => setOpen(true));

    const [loadingCodeEditor, setLoadingCodeEditor] = useState(false)
    useEffect(() => {
        setCode(dataset.payload)
        setLoadingCodeEditor(true)
        setTimeout(() => {
            setLoadingCodeEditor(false)
        }, 0)
    }, [dataset])

    // @ts-ignore
    const md = '```json /color/ \n' + (result && result.data && JSON.stringify(result.data, null, 2)) + '\n```'

    // @ts-ignore
    return (
        <>
            <div className="mb-5">
                <span className="text-muted-foreground mr-2"> 预置规则集 </span>
                <DatasetSelector datasets={droolsDataDatasets} dataset={dataset} setDataset={setDataset} />
                <div className="float-right">
                    <Dialog open={open} onOpenChange={setOpen}>
                        <DialogTrigger asChild>
                            <Button
                                variant="outline"
                                // loading={compilerLoading}
                                // @ts-ignore
                                disabled={saveLoading}
                                onClick={() => { // @ts-ignore
                                    setSaveLoading(true);}}
                            >
                                <Loading loading={!!saveLoading} className="w-4 h-4 mr-2">
                                    <TerminalIcon  className={"w-4 h-4 mr-2"} />
                                </Loading>
                                运行
                            </Button>
                        </DialogTrigger>
                        <DialogContent className="max-w-[825px] h-[600px]">
                            <DialogHeader>
                                <DialogTitle>运行结果</DialogTitle>
                                <DialogDescription>返回运行后的数据</DialogDescription>
                            </DialogHeader>
                            <div className="grid gap-4 py-4 w-full overflow-y-auto">
                                {
                                    // @ts-ignore
                                    result && result.data &&  <ByteViewer value={md} />

                                }
                            </div>
                        </DialogContent>
                    </Dialog>

                </div>
            </div>
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

        </>
    )
}
