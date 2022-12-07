export class ArquivoUtils {


    public static download(response: any, name:string):void{
        const data = response.body;
		const blob = new Blob([data], { type: 'application/pdf' });

		const urlDownload = window.URL.createObjectURL(blob);
		const link = document.createElement('a');
		document.body.appendChild(link);
		link.href = urlDownload;
		link.download = name;
		link.click();
		URL.revokeObjectURL(urlDownload);
		//setTimeout(function () { window.URL.revokeObjectURL(urlDownload); }, 0);
    }
}